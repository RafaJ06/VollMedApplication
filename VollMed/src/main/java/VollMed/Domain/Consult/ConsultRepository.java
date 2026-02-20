package VollMed.Domain.Consult;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
   boolean existsByDoctorIdAndAppointment(Long aLong, @Future @NotNull LocalDateTime appointment);

   boolean existsByPatientIdAndAppointmentAndMotiveIsNull(@NotNull Long aLong, LocalDateTime appointment);
}
