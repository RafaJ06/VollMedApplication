package VollMed.Domain.Doctor;

import VollMed.Domain.Address.AddressInformationDTO;
import jakarta.validation.Valid;

public record UpdateDoctor(

        Long id,
        String name,
        String phoneNumber,
        @Valid AddressInformationDTO address
) {
}