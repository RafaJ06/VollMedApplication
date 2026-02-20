package VollMed.Domain.Consult;

import VollMed.Domain.Doctor.Doctor;
import VollMed.Domain.Patient.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consult")
@Entity(name = "Consult")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="doctor_id")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="patient_id")
    private Patient patient;
    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private CancellationMotive motive;
    private boolean cancel;
    private LocalDateTime appointment;

    public Consult(Long id, Doctor doctor, Patient patient, @Future @NotNull LocalDateTime appointment) {
    }

    public void cancel(CancelConsult cancelConsult){
        this.motive = cancelConsult.motive();
    }
}


