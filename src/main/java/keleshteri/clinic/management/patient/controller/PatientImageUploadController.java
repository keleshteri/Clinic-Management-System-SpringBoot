package keleshteri.clinic.management.patient.controller;

import keleshteri.clinic.management.patient.service.PatientImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/v1/patient/images")
public class PatientImageUploadController {


    @Autowired
    PatientImageService patientImageService;

    @PostMapping("/{patientId}/upload")
    public ResponseEntity uploadImage(@Valid @RequestParam MultipartFile imageFile, @PathVariable (value = "patientId") Long patientId)  throws IOException{
        System.out.println("here controller");
        System.out.println(patientId);
        System.out.println(imageFile);
        return this.patientImageService.uploadToLocalFileSystem(imageFile,patientId);
//        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(
            value = "getImage/{imageName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE}
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException {
        return this.patientImageService.getImageWithMediaType(fileName);
    }

//    @PostMapping(value = "/upload",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//            produces = MediaType.MULTIPART_FORM_DATA_VALUE
////            produces = MediaType.APPLICATION_XML_VALUE,
////            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    public ResponseEntity.BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

//    }
//
//
//
//    @GetMapping(path = { "/get/{imageName}" })
//    public PatientImage getImage(@PathVariable("imageName") String imageName) throws IOException {
//        final Optional<PatientImage> retrievedImage = imageRepository.findByName(imageName);
//        PatientImage img = new PatientImage(retrievedImage.get().getName(), retrievedImage.get().getType(),
//                decompressBytes(retrievedImage.get().getPicByte()));
//        return img;
//    }


}
