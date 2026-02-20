package VollMed.Domain.Doctor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    Doctor findByActiveTrueAndId(Long id);

    @Query("""
            select d from Doctor d
            where
            active = true
            and
            specialization = :specialization
            and
            d.id not in (
                    select c.doctor.id from Consult c
                    where
                    c.appointment = :appointment
                    and
                    c.motive is null)
            order by rand()
            limit 1
            """)
    Doctor PickUpAvailableDoctorForAppointment(Specialization specialization, @Future @NotNull LocalDateTime appointment);

    @Query("""
            select d.active
            from Doctor d
            where
            d.id = :idDoctor
            """)
    boolean findActiveById(Long idDoctor);
}


