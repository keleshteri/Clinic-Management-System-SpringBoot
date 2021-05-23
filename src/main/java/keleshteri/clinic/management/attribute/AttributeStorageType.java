package keleshteri.clinic.management.attribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum AttributeStorageType {
    TEXT("text"),
    INTEGER("integer"),
    DOUBLE("double"),
    DATE("date"),
    DATETIME("datetime"),
    BOOLEAN("boolean "),
    JSON("json");

    private final String label;


    AttributeStorageType(String label) {
        this.label = label;
    }
}
