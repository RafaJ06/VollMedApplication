package VollMed.Domain.Consult.Validations.Reservation;

import VollMed.Domain.Consult.ConsultRepository;
import VollMed.Domain.Consult.ConsultRequest;
import VollMed.Domain.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorAgainstOtherConsult implements ConsultValidator {

    @Autowired
    private ConsultRepository repository;

    public void validate(ConsultRequest consultRequest){
        var doctorHaveAnotherConsultAtTheSameTime =  repository.existsByDoctorIdAndAppointment(consultRequest.doctorId() ,consultRequest.appointment());

        if(doctorHaveAnotherConsultAtTheSameTime) throw  new ValidationException("Doctor already have a consult at this time");
    }
}
