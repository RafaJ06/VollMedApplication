package VollMed.Patient;

import VollMed.Doctor.Specialization;

import java.util.List;

public record PatientListDTO(
        String name,
        String email,
        String document
) {
    public PatientListDTO(Patient patients) {
        this(
                patients.getName(),
                patients.getEmail(),
                patients.getDocument());
    }

}
