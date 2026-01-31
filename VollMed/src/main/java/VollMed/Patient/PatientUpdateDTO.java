package VollMed.Patient;

import VollMed.Address.Address;
import VollMed.Address.AddressInformationDTO;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateDTO(
        @NotBlank Long id,
        String name,
        String phoneNumber,
        Address address
) {
}
