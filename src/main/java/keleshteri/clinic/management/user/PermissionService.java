package keleshteri.clinic.management.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {


    private final  PermissionRepository permissionRepository;
    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }





    public List<Permission> all(){
        return permissionRepository.findAll();
    }


    public void create(Permission permission){
        permissionRepository.save(permission);
    }


    public void seeder(){
        if (permissionRepository.count() == 0) {
            Permission STUDENT_READ = new Permission("student:read","student:read");
            Permission STUDENT_WRITE = new Permission("student:write","student:write");
            Permission COURSE_READ = new Permission("course:read","course:read");
            Permission COURSE_WRITE = new Permission("course:write","course:write");
            permissionRepository.save(STUDENT_READ);
            permissionRepository.save(STUDENT_WRITE);
            permissionRepository.save(COURSE_READ);
            permissionRepository.save(COURSE_WRITE);
        }
        System.out.println(permissionRepository.count());
    }
}
