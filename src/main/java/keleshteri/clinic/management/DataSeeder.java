package keleshteri.clinic.management;

import keleshteri.clinic.management.global.quantity.QuantityService;
import keleshteri.clinic.management.global.units_measurement.UnitsMeasurementService;
import keleshteri.clinic.management.locale.Locale;
import keleshteri.clinic.management.locale.LocaleService;
import keleshteri.clinic.management.pharmacy.service.MedicineCompanyService;
import keleshteri.clinic.management.pharmacy.service.MedicineService;
import keleshteri.clinic.management.user.PermissionService;
import keleshteri.clinic.management.user.RoleService;
import keleshteri.clinic.management.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    LocaleService localeService;

    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @Autowired
    QuantityService quantityService;

    @Autowired
    UserService userService;

    @Autowired
    UnitsMeasurementService unitsMeasurementService;

    @Autowired
    MedicineService medicineService;

    @Autowired
    MedicineCompanyService medicineCompanyService;

    @Override
    public void run(String... args) throws Exception {
        localeService.seeder();
        permissionService.seeder();
        roleService.seeder();
        quantityService.seeder();
        unitsMeasurementService.seeder();
        medicineService.seeder();
        medicineCompanyService.seeder();

        userService.seeder();
        System.out.println("DataSeeder");
    }
}
