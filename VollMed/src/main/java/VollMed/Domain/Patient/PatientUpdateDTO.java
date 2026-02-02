package VollMed.Domain.Patient;

import VollMed.Domain.Address.Address;
import jakarta.validation.constraints.NotBlank;

public record PatientUpdateDTO(
        @NotBlank Long id,
        String name,
        String phoneNumber,
        Address address
) {
}
