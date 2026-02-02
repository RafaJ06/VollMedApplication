package VollMed.Domain.Patient;

import VollMed.Domain.Address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "patient")
@Entity(name = "Patient")
public class Patient {
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public String getDocument() {
        return document;
    }

    public Address getAddress() {
        return address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone_Number;
    private String document;
    @Embedded
    private Address address;
    private boolean active;

    public Patient(PatientDTO patient) {

        this.id = null;
        this.name = patient.name();
        this.email = patient.email();
        this.address = new Address(patient.address());
        this.document = patient.document();
        this.phone_Number = patient.phoneNumber();
    }

    public void Update(PatientUpdateDTO patient) {

        if (patient.name() != null && !patient.name().isBlank()) this.name = patient.name();
        if (patient.phoneNumber() != null && !patient.phoneNumber().isBlank()) this.phone_Number = patient.phoneNumber();
        if (patient.address() != null) this.address = patient.address();
    }

    public void Delete() {

        this.active = false;
    }
}
