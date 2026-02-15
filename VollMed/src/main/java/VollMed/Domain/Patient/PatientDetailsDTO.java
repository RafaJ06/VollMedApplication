package VollMed.Domain.Patient;

import VollMed.Domain.Address.Address;

public record PatientDetailsDTO(Long id, String name, String email, String phone_Number, String document,
                                Address address) {
    public PatientDetailsDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone_Number(), patient.getDocument(), patient.getAddress());
    }
}