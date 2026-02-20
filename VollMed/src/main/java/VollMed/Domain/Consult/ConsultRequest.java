package VollMed.Domain.Consult;

import VollMed.Domain.Doctor.Specialization;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record ConsultRequest(

        Long doctorId,

        @NotNull
        Long patientId,

        @Future
        @NotNull
        LocalDateTime appointment,
        Specialization specialization
) {
}
