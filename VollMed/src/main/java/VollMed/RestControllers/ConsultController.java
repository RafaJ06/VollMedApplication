package VollMed.RestControllers;

import VollMed.Domain.Consult.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class ConsultController {
    @Autowired
    ConsultRepository repository;
    @Autowired
    ScheduleAppointment scheduleAppointment;


    @Transactional
    @PostMapping
    public ResponseEntity makeUpConsult(@RequestBody @Valid ConsultRequest consultRequest) {

        ConsultDetails consult = scheduleAppointment.toBookAppointment(consultRequest);

        return ResponseEntity.ok(consult);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity cancelConsult(@RequestBody @Valid CancelConsult cancelConsult){

       scheduleAppointment.cancel(cancelConsult);

       return ResponseEntity.noContent().build();
    }
}
