package VollMed.RestControllers;
import VollMed.Domain.Doctor.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping({"/doctor"})
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    @Autowired
    DoctorRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity signUp(@RequestBody @Valid DoctorDTO doctor, UriComponentsBuilder uriComponentsBuilder) {
        Doctor doctorDetail = new Doctor(doctor);
        this.repository.save(doctorDetail);
        URI uri = uriComponentsBuilder.path("/doctor/ShowDoctor/{id}").buildAndExpand(new Object[]{doctorDetail.getId()}).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetails(doctorDetail));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorsList>> ShowDoctors(@PageableDefault(size = 10,sort = {"name"}) Pageable pagination) {
        Page<DoctorsList> page = this.repository.findAllByActiveTrue(pagination).map(DoctorsList::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping({"/ShowDoctor/{id}"})
    public ResponseEntity<DoctorsList> ShowDoctor(@PathVariable Long id) {
        Doctor doctor = this.repository.findByActiveTrueAndId(id);
        return ResponseEntity.ok(new DoctorsList(doctor));
    }

    @Transactional
    @PutMapping
    public ResponseEntity update(@RequestBody @Valid UpdateDoctor doctorUpdate) {
        Doctor doctor = (Doctor)this.repository.getReferenceById(doctorUpdate.id());
        doctor.UpdateDoctor(doctorUpdate);
        return ResponseEntity.ok(new DoctorDetails(doctor));
    }

    @Transactional
    @DeleteMapping({"/{id}"})
    public ResponseEntity delete(@PathVariable Long id) {
        Doctor doctor = (Doctor)this.repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }
}
