package VollMed.Domain.Consult.Validations.Reservation;

import VollMed.Domain.Consult.ConsultRequest;

public interface ConsultValidator {
    void validate(ConsultRequest consultRequest);
}
