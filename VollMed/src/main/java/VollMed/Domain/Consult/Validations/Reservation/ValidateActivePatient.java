package VollMed.Domain.Consult.Validations.Reservation;

import VollMed.Domain.Consult.ConsultRequest;
import VollMed.Domain.Patient.PatientRepository;
import VollMed.Domain.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActivePatient implements ConsultValidator {
    @Autowired
    private PatientRepository repository;

    public void validate(ConsultRequest consultRequest){
        if(consultRequest.patientId() == null) return;

        var patientStateActive = repository.findActiveById(consultRequest.patientId());
        if(!patientStateActive) throw new ValidationException("This patient is not active!");
    }
}
