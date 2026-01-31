package VollMed.Doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
@Entity(name = "Doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private boolean active;
    private String name;
    private String email;
    private String phoneNumber;
    private String document;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @Embedded
    private Address address;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDocument() {
        return document;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Address getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public Doctor(DoctorDTO doctor) {

        this.id = null;
        this.name = doctor.name();
        this.email = doctor.email();
        this.address = new Address(doctor.addressInformation());
        this.document = doctor.document();
        this.specialization = doctor.specialization();
        this.phoneNumber = doctor.phoneNumber();

    }

    public void UpdateDoctor(@Valid UpdateDoctor doctorUpdate) {
        if(doctorUpdate.name() != null){
            this.name = doctorUpdate.name();
        }
        if (doctorUpdate.phoneNumber() != null){
            this.phoneNumber = doctorUpdate.phoneNumber();
        }
        if (doctorUpdate.address() != null){
            this.address.UpdateAddress(doctorUpdate.address());
        }
    }

    public void delete() {
     this.active = false;
    }
}
