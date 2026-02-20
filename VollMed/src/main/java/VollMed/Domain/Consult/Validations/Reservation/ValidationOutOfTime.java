package VollMed.Domain.Consult.Validations.Reservation;

import VollMed.Domain.Consult.ConsultRequest;
import VollMed.Domain.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidationOutOfTime implements ConsultValidator {

    public void validate(ConsultRequest consultRequest){

        var consultDate = consultRequest.appointment();
                var sunday = consultDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
                var hourBeforeClinicOpening = consultDate.getHour() <7;
                var hourAfterClinicEnding = consultDate.getHour() > 18;

                if(sunday || hourAfterClinicEnding || hourBeforeClinicOpening){
                    throw new ValidationException("Selected hour is out of the attending time");
                }
    }
}
