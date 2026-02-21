package VollMed.RestControllers;

import VollMed.Domain.Patient.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping({"Patient"})
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    @Autowired
    PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity SignUpPatient(@RequestBody @Valid PatientDTO patient, UriComponentsBuilder uriComponentsBuilder) {
        Patient patientDetails = (Patient)this.repository.save(new Patient(patient));
        URI uri = uriComponentsBuilder.path("/Patient/ShowPatient/{id}").buildAndExpand(new Object[]{patientDetails.getId()}).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsDTO(patientDetails));
    }

    @GetMapping
    public ResponseEntity<PagedModel<PatientListDTO>> ShowPatients(@PageableDefault(size = 10,sort = {"name"}) Pageable pagination, PagedResourcesAssembler assembler) {
        Page<PatientListDTO> patients = this.repository.findAllByActiveTrue(pagination).map(PatientListDTO::new);
        return ResponseEntity.ok().body(assembler.toModel(patients));
    }

    @GetMapping({"/ShowPatient/{id}"})
    public ResponseEntity<PatientListDTO> ShowPatient(@PathVariable Long id) {
        Patient patient = this.repository.findByActiveTrueAndId(id);
        return patient != null ? ResponseEntity.ok(new PatientListDTO(patient)) : ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity UpdatePatient(@RequestBody PatientUpdateDTO patientData) {
        Patient patient = (Patient)this.repository.getReferenceById(patientData.id());
        patient.Update(patientData);
        return ResponseEntity.ok().body(new PatientDetailsDTO(patient));
    }

    @Transactional
    @DeleteMapping({"/{id}"})
    public ResponseEntity DeletePatient(@PathVariable Long id) {
        Patient patient = (Patient)this.repository.getReferenceById(id);
        patient.Delete();
        return ResponseEntity.noContent().build();
    }
}
