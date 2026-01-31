package VollMed.Doctor;

public record UpdateDoctor(

        Long id,
        String name,
        String phoneNumber,
        AddressInformationDTO address
) {
}