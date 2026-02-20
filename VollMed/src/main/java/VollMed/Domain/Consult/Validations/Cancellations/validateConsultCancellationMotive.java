package VollMed.Domain.Consult.Validations.Cancellations;

import VollMed.Domain.Consult.CancelConsult;
import VollMed.Domain.Consult.ConsultRepository;
import VollMed.Domain.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class validateConsultCancellationMotive {

    @Autowired
    ConsultRepository repository;

    public void validate(CancelConsult cancelConsult) {

        var consulta = repository.getReferenceById(cancelConsult.id());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, consulta.getAppointment()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("The consult can not be cancellated 24 before attending!");
        }
    }


}
