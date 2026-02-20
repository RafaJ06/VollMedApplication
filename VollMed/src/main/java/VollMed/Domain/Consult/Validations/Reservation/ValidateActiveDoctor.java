package VollMed.Domain.Consult.Validations.Reservation;

import VollMed.Domain.Consult.ConsultRequest;
import VollMed.Domain.Doctor.DoctorRepository;
import VollMed.Domain.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActiveDoctor implements ConsultValidator {

    @Autowired
    private DoctorRepository repository;

    public void validate(ConsultRequest consultRequest){
        if(consultRequest.doctorId() == null) return;

        var doctorStateActive = repository.findActiveById(consultRequest.doctorId());
        if(!doctorStateActive) throw new ValidationException("This doctor is not active!");
    }
}
