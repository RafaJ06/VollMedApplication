package VollMed.Domain.Consult;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultDetails(
       Long doctor_id,
       Long id,
       Long patient_id,
       LocalDateTime appointment
) {
    public ConsultDetails(Consult consult) {
        this(consult.getDoctor().getId(), consult.getId(), consult.getPatient().getId(), consult.getAppointment());
    }
}
