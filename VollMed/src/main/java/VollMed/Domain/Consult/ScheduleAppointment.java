package VollMed.Domain.Consult;

import VollMed.Domain.Consult.Validations.Reservation.ConsultValidator;
import VollMed.Domain.Consult.Validations.Cancellations.validateConsultCancellationMotive;
import VollMed.Domain.Doctor.Doctor;
import VollMed.Domain.Doctor.DoctorRepository;
import VollMed.Domain.Patient.PatientRepository;
import VollMed.Domain.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointment {
    @Autowired
    private ConsultRepository consultRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    List<ConsultValidator> consultValidators;
    @Autowired
    private List<validateConsultCancellationMotive> validateConsultCancellationMotive;

    public ConsultDetails toBookAppointment(ConsultRequest consultRequest) {

        if (!patientRepository.existsById(consultRequest.patientId())) {
            throw new ValidationException("This patient id doesn't exist");
        }
        if (consultRequest.doctorId() != null && !doctorRepository.existsById(consultRequest.doctorId())) {
            throw new ValidationException("This doctor id doesn't exist");
        }

        consultValidators.forEach(v -> v.validate(consultRequest));

        var patient = patientRepository.findById(consultRequest.patientId()).get();
        var doctor = pickUpDoctor(consultRequest);

        if (doctor == null) {
            throw new ValidationException("This doctor is not available at this time");
        }

        Consult consult;
        consult = new Consult(null, doctor, patient, consultRequest.appointment());
        consultRepository.save(consult);
        return new ConsultDetails(consult);
    }

    private Doctor pickUpDoctor(ConsultRequest consultRequest) {

        if (consultRequest.doctorId() != null) {
            return doctorRepository.getReferenceById(consultRequest.doctorId());
        }
        if (consultRequest.specialization() == null) {
            throw new ValidationException("You need to provide a speciality");
        }
        return doctorRepository.PickUpAvailableDoctorForAppointment(consultRequest.specialization(), consultRequest.appointment());
    }

    public void cancel (CancelConsult cancelConsult){

        if (!consultRepository.existsById(cancelConsult.id())) {
            throw new ValidationException("Id does not exist!");
        }
        validateConsultCancellationMotive.forEach(v -> v.validate(cancelConsult));

        var consult = consultRepository.getReferenceById(cancelConsult.id());
        consult.cancel(cancelConsult);

    }
}
