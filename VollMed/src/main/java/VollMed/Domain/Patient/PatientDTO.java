package VollMed.Domain.Patient;

import VollMed.Domain.Address.AddressInformationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientDTO(

        @NotBlank String name,
        @Email @NotBlank String email,
        @NotNull @NotBlank @Pattern(regexp = "\\d{10}") String phoneNumber,
        @NotBlank @Pattern(regexp = "^\\d{11}$") String document,
        @NotNull @Valid AddressInformationDTO address) {
}
