package VollMed.Domain.Doctor;

import VollMed.Domain.Address.AddressInformationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorDTO(

        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String phoneNumber,
        @NotBlank @Pattern(regexp = "^\\d{11}$") String document,
        @NotNull Specialization specialization,
        @NotNull @Valid AddressInformationDTO addressInformation
) {
}
