package VollMed.Domain.Patient;

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
