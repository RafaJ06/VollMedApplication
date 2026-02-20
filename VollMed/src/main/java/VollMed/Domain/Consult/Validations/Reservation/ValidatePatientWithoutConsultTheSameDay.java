package VollMed.Domain.Consult.Validations.Reservation;

import VollMed.Domain.Consult.ConsultRepository;
import VollMed.Domain.Consult.ConsultRequest;
import VollMed.Domain.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientWithoutConsultTheSameDay implements ConsultValidator {

    @Autowired
    private ConsultRepository repository;

    public  void validate(ConsultRequest consultRequest){
        var appointment = consultRequest.appointment().withHour(7);
        var schedule = consultRequest.appointment().withHour(18);
        var patientHaveAnotherConsultThroughTheDay = repository.existsByPatientIdAndAppointmentAndMotiveIsNull(consultRequest.patientId(), appointment);

        if(patientHaveAnotherConsultThroughTheDay) throw new ValidationException("Patient already have a consult that day");

    }
}
