package keleshteri.clinic.management.locale;

import keleshteri.clinic.management.GlobalService;
import keleshteri.clinic.management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LocaleService implements GlobalService<Locale> {

    @Autowired
    LocaleRepository localeRepository;


    @Override
    public List<Locale> all() {
        return localeRepository.findAll();
    }

    @Override
    public ResponseEntity<Locale> find(Long id) {
        Locale locale = localeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locale not exist with id :" + id));
        return ResponseEntity.ok(locale);
    }

    @Override
    public ResponseEntity<List<Locale>> pagination() {
        return null;
    }

    @Override
    public ResponseEntity<Locale> create(Locale locale) {
        return ResponseEntity.ok(localeRepository.save(locale));
    }

    @Override
    public ResponseEntity<Locale> update(Long id, Locale locale) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> delete(Long id) {
        return null;
    }

    public void seeder(){
        if (localeRepository.count() == 0) {
            Locale locale_en = new Locale("en_US");
            Locale locale_de = new Locale("de_DE");
            Locale locale_fr = new Locale("fr_FR");
            Locale locale_es = new Locale("es_ES");
            Locale locale_fa = new Locale("fa_IR");
            localeRepository.save(locale_en);
            localeRepository.save(locale_de);
            localeRepository.save(locale_fr);
            localeRepository.save(locale_es);
            localeRepository.save(locale_fa);
        }
        System.out.println(localeRepository.count());
    }
}
