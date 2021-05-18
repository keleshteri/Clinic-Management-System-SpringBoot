package keleshteri.clinic.management.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }


    public List<Role> all(){
        return roleRepository.findAll();
    }


    public void create(Role role){
        roleRepository.save(role);
    }


    public void seeder(){
        if (roleRepository.count() == 0) {
//            Permission permission = permissionRepository.
//            Set<Permission> permissionsSTUDENT = null;
//            Permission STUDENT_READ = permissionRepository.findByName("student:read").get();
//            permissionsSTUDENT.add(STUDENT_READ.getName());
//            System.out.println("RoleService");
            
//            System.out.println(STUDENT_READ);

            Role STUDENTRole = new Role("STUDENT","STUDENT");
            Role adminRole = new Role("ADMIN","admin");
            Role userRole = new Role("ADMINTRAINEE","ADMINTRAINEE");



            roleRepository.save(STUDENTRole);
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }
        System.out.println(roleRepository.count());
    }

}
