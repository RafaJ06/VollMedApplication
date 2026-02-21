package VollMed.Domain.Doctor;

import VollMed.Domain.Address.Address;
import VollMed.Domain.Address.AddressInformationDTO;
import VollMed.Domain.Consult.CancellationMotive;
import VollMed.Domain.Consult.Consult;
import VollMed.Domain.Patient.Patient;
import VollMed.Domain.Patient.PatientDTO;
import VollMed.Domain.Patient.PatientDetailsDTO;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:resources/application-test.yaml")
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository repository;

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("this must return null when the a doctor exist but is not available at that hour")
    void pickUpAvailableDoctorForAppointmentScenery1() {
        var mondayNextWeekAt10AM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = signUpDoctor("jose", "jose@gmail.com", "12331232190", Specialization.ORTOPEDIA);
        var patient = singUpPatient("hola", "hola@gmail.com", "312321");
     bookConsult(doctor, patient, mondayNextWeekAt10AM);

        var doctorAvailable = repository.PickUpAvailableDoctorForAppointment(Specialization.ORTOPEDIA, mondayNextWeekAt10AM);
        assertThat(doctorAvailable).isNotNull();
    }

    @Test
    @DisplayName("this must return doctor when the a doctor is available at that hour")
    void pickUpAvailableDoctorForAppointmentScenery2() {
        var mondayNextWeekAt10AM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = signUpDoctor("jose", "jose@gmail.com", "09909090978", Specialization.ORTOPEDIA);

        var doctorAvailable = repository.PickUpAvailableDoctorForAppointment(Specialization.ORTOPEDIA, mondayNextWeekAt10AM);

        assertThat(doctorAvailable).isEqualTo(doctor);
    }

    private PatientDTO patientDetails(String name, String email, String nationalID){
        return new PatientDTO(name, email, nationalID, "66686", addressInformation());
    }

    private  void bookConsult(Doctor doctor, Patient patient, LocalDateTime date){
        em.persist(new Consult(null, doctor, patient, CancellationMotive.DOCTOR_CANCELLED, false,date ));
    }

    private DoctorDTO doctorDetails(String name, String email, String nationalID, Specialization specialization ){
        return new DoctorDTO(name, email, "887979879", nationalID,specialization, addressInformation());
    }
private Doctor signUpDoctor(String name, String email, String nationalId, Specialization specialization){
        var doctor = new Doctor((doctorDetails(name, email, nationalId, specialization)));
        em.persist(doctor);
        return doctor;
}

    private Patient singUpPatient(String name, String email, String nationalId){
        var patient = new Patient(patientDetails(name,email, nationalId));
        em.persist(patient);
        return patient;

    }

    private AddressInformationDTO addressInformation(){
        return
                new AddressInformationDTO(
                "THREE",
                null,
                null,
                "SD",
                "SND",
                "NA",
                "23421");

    }
}