package VollMed.Doctor;

public record DoctorsList(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String document,
        Specialization specialization
     ) {

    public DoctorsList(Doctor doctor) {

//In order to this "this" to work is necessary to have all the gets in the same order as the attributes/variables
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhoneNumber(),
                doctor.getDocument(),
                doctor.getSpecialization()

        );
    }
}


