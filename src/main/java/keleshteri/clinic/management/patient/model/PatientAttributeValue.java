package keleshteri.clinic.management.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter @Setter
@Entity
@Table(name = "patient_attribute_value")
public class PatientAttributeValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Patient patient;


    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_attribute_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PatientAttribute patientAttribute;

    @Column(name = "text_value",columnDefinition = "TEXT")
    private String text_value;

    @Column(name = "integer_value")
    private Long integer_value;

    @Column(name = "boolean_value")
    private Boolean boolean_value;

    @Column(name = "double_value")
    private Double double_value;

    @Column(name = "datetime_value")
    private Date datetime_value;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_value")
    private Date date_value;

    @Column(name = "json_value", columnDefinition = "json")
    @JsonRawValue
    private String json_value;

}
