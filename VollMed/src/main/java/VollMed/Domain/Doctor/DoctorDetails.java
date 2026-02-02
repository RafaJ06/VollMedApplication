package VollMed.Domain.Doctor;

import VollMed.Domain.Address.Address;

public record DoctorDetails(Long id, String name, String email, String phoneNumber, String document,
                            Specialization specialization, Address address) {
    public DoctorDetails(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhoneNumber(), doctor.getDocument(), doctor.getSpecialization(), doctor.getAddress());
    }
}