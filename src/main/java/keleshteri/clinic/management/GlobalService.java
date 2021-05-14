package keleshteri.clinic.management;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface GlobalService<T> {

    public List<T> all();

    public ResponseEntity<T> find(Long id);

    public ResponseEntity<List<T>> pagination();

    public ResponseEntity<T> create(T t);

    public ResponseEntity<T> update(Long id,T t);

    public ResponseEntity<Map<String, Boolean>> delete(Long id);
}
