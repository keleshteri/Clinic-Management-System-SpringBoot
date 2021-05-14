package keleshteri.clinic.management.person;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
//@JsonSerialize(using = GenderSerializer.class)
@Getter
public enum Gender {
    FEMALE("female",1),
    MALE("male",2);

    private final String label;
    private final Integer code;

    private  Gender(String value,int code){
        this.label = value;
        this.code = code;
    }



    @JsonCreator
    public static Gender decode(final String name) {
        return Stream.of(Gender.values()).filter(targetEnum -> targetEnum.name().equals(name)).findFirst().orElse(null);
    }

//    @JsonValue
//    public Integer getCode() {
//        return code;
//    }

    @JsonValue
    public String getName() {
        return this.name();
    }
}
