package VollMed.Domain.Patient;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByActiveTrue(Pageable pagination);

    Patient findByActiveTrueAndId(Long id);

    @Query("""
        select p.active
        from Patient p
        where
        p.id = :idPatient
        """)
    boolean findActiveById(@NotNull Long idPatient);
}
