package VollMed.Domain.Consult.Validations.Reservation;

import VollMed.Domain.Consult.ConsultRequest;
import VollMed.Domain.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidationAnticipatedConsult implements ConsultValidator {

    public void validate(ConsultRequest consultRequest){
        var consultHour = consultRequest.appointment();
        var now = LocalDateTime.now();
        var minutesDifference = Duration.between(now, consultHour).toMinutes();
        if (minutesDifference < 30) throw new ValidationException("There have to be a difference at least of 30 minutes");
    }
}
