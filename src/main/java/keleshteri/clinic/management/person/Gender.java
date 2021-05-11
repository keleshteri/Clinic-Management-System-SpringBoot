package keleshteri.clinic.management.person;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
//@JsonSerialize(using = GenderSerializer.class)
@Getter
public enum Gender {

    MALE("male",1),
    FEMALE("female",2);

    private final String label;
    private final Integer code;

    Gender(String value,int code){
        this.label = value;
        this.code = code;
    }

    public String getName() {
        return this.name();
    }

}
