package keleshteri.clinic.management;

import keleshteri.clinic.management.locale.Locale;
import keleshteri.clinic.management.locale.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    LocaleService localeService;
    @Override
    public void run(String... args) throws Exception {
        localeService.seeder();
        System.out.println("DataSeeder");
    }
}
