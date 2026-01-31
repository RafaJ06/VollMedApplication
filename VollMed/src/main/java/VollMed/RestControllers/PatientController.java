package VollMed.RestControllers;

import VollMed.Patient.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Patient")
public class PatientController {

    @Autowired
    PatientRepository repository;

    @PostMapping
    @Transactional
    public void SignUpPatient(@RequestBody @Valid PatientDTO patient){

        repository.save(new Patient(patient));
    }

    @GetMapping
    public PagedModel<PatientListDTO> ShowPatients(@PageableDefault(size = 10, sort = {"name"})
                                                       Pageable pagination,  PagedResourcesAssembler assembler){

       var patients = repository.findAllByActiveTrue(pagination).map(PatientListDTO::new);
        return assembler.toModel(patients) ;
    }

    @Transactional
    @PutMapping
    public  void UpdatePatient(@RequestBody PatientUpdateDTO patientData){

       var patient = repository.getReferenceById(patientData.id());

       patient.Update(patientData);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void DeletePatient(@PathVariable Long id){

        var patient = repository.getReferenceById(id);

        patient.Delete();

    }
}
