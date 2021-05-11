package keleshteri.clinic.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecordExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RecordExistsException(){
        super();
    }

    public RecordExistsException(String message){
        super(message);
    }
}
