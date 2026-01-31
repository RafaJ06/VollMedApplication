package VollMed.RestControllers;
import VollMed.Doctor.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorRepository repository;

    @Transactional
    @PostMapping
    public void signUp(@RequestBody @Valid DoctorDTO doctor){

       repository.save(new Doctor(doctor));
    }

    @GetMapping
    public Page<DoctorsList> ShowDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){

        //Transformar a Pagable para darle paginacion a la consulta
       // return repository.findAll().stream().map(DoctorsList::new).collect(Collectors.toList());
        return repository.findAllByActiveTrue(pagination).map(DoctorsList::new);
    }

    @Transactional
    @PutMapping
    public void update(@RequestBody @Valid UpdateDoctor doctorUpdate){
       var doctor = repository.getReferenceById(doctorUpdate.id());
        doctor.UpdateDoctor(doctorUpdate);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }
}
