package keleshteri.clinic.management.patient.service;

import keleshteri.clinic.management.exception.ResourceNotFoundException;
import keleshteri.clinic.management.patient.model.Patient;
import keleshteri.clinic.management.patient.model.PatientImage;
import keleshteri.clinic.management.patient.repository.PatientImageRepository;
import keleshteri.clinic.management.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class PatientImageService {

    @Autowired
    PatientImageRepository patientImageRepository;
    @Autowired
    PatientRepository patientRepository;

    public final String storageDirectoryPath = "D:\\uploads\\mediumImages";

    //uploadToLocalFileSystem
    public ResponseEntity uploadToLocalFileSystem(MultipartFile file,Long patientId)  throws IOException {



        /* we will extract the file name (with extension) from the given file to store it in our local machine for now
        and later in virtual machine when we'll deploy the project
         */
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        //patientRepository
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + patientId));


        //save on database
//        PatientImage patientImage = new PatientImage(file.getOriginalFilename(), file.getContentType(),
//                    compressBytes(file.getBytes()));
        //
        PatientImage patientImage = new PatientImage();
        patientImage.setPatient(patient);
        patientImage.setName(file.getOriginalFilename());
        patientImage.setType(file.getContentType());
        //save image on db
        patientImage.setPicByte(compressBytes(file.getBytes()));
        patientImageRepository.save(patientImage);




        /* The Path in which we will store our image . we could change it later
        based on the OS of the virtual machine in which we will deploy the project.
        In my case i'm using windows 10 .
         */
        Path storageDirectory = Paths.get(storageDirectoryPath);
        /*
         * we'll do just a simple verification to check if the folder in which we will store our images exists or not
         * */
        if(!Files.exists(storageDirectory)){ // if the folder does not exist
            try {
                Files.createDirectories(storageDirectory); // we create the directory in the given storage directory path
            }catch (Exception e){
                e.printStackTrace();// print the exception
            }
        }

        Path destination = Paths.get(storageDirectory.toString() + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);// we are Copying all bytes from an input stream to a file

        } catch (IOException e) {
            e.printStackTrace();
        }
        // the response will be the download URL of the image
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/patient/images/getImage/")
                .path(fileName)
                .toUriString();
        // return the download image url as a response entity
        return ResponseEntity.ok(fileDownloadUri);
    }

    //get image from folder
    public  byte[] getImageWithMediaType(String imageName) throws IOException {

        Path destination = Paths.get(storageDirectoryPath+"\\"+imageName);// retrieve the image by its name

        return IOUtils.toByteArray(destination.toUri());
    }

    //find image and get from database
    public PatientImage getImage(String imageName) throws IOException {
        final Optional<PatientImage> retrievedImage = patientImageRepository.findByName(imageName);
        PatientImage img = new PatientImage(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

        // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

}
