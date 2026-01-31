package VollMed.Doctor;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

   private String street;
   private String number;
   private String unit;
   private String district;
   private String city;
   private String state;
   private String postalCode;

    public Address(AddressInformationDTO addressInformation) {

        this.street = addressInformation.street();
        this.number = addressInformation.number();
        this.unit = addressInformation.unit();
        this.district = addressInformation.district();
        this.state = addressInformation.state();
        this.postalCode = addressInformation.postalCode();
        this.city = addressInformation.city();

    }

    public void UpdateAddress(AddressInformationDTO address) {

        if (address.city()!=null){
            this.city = address.city();
        }
        if (address.district() != null){
            this.street = address.street();
        }
        if (address.postalCode() != null){
            this.postalCode = address.postalCode();
        }
        if (address.state() != null){
            this.state = address.state();
        }
        if(address.number() != null) this.number = address.number();
        if (address.district() != null) this.district = address.district();
        if (address.unit() != null) this.unit = address.unit();
    }
}
