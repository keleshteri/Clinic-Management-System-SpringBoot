package keleshteri.clinic.management.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/gender")
public class GenderController {

//    @GetMapping()
//    public Map<String, String> sayHello() {
//        HashMap<String, String> map = new HashMap<>();
//        map.forEach("name",Gender.values());
//        map.put("key", "value");
//        map.put("foo", "bar");
//        map.put("aa", "bb");
//        return map;
//    }

    @GetMapping()
    public List<Gender> test(){
       return Arrays.stream(Gender.values()).toList();
    }
}
