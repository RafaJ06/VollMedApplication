package VollMed.Doctor;

import VollMed.Address.AddressInformationDTO;
import jakarta.validation.Valid;

public record UpdateDoctor(

        Long id,
        String name,
        String phoneNumber,
        @Valid AddressInformationDTO address
) {
}