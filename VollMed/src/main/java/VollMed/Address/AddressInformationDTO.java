package VollMed.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressInformationDTO(

        @NotBlank String street,
        String number,
        String unit,
        @NotBlank  String district,
        @NotBlank  String city,
        @NotBlank  String state,
        @NotBlank @Pattern(regexp = "^\\d{5}$") String postalCode) {
}
