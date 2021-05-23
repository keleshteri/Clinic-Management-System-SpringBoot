package keleshteri.clinic.management.attribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum AttributeType {
    TEXT("text", AttributeStorageType.TEXT),
    TEXTAREA("textarea", AttributeStorageType.TEXT),
    CHECKBOX("checkbox", AttributeStorageType.BOOLEAN),
    NUMBER("number", AttributeStorageType.INTEGER),
    PERCENT("percent",AttributeStorageType.DOUBLE ),
    DATETIME("datetime", AttributeStorageType.DATETIME),
    DATE("date", AttributeStorageType.DATE),
    SELECT("select", AttributeStorageType.JSON);

    private final String label;

    private final AttributeStorageType storageType;

    AttributeType(String label, AttributeStorageType storageType) {
        this.label = label;
        this.storageType = storageType;
    }
}
