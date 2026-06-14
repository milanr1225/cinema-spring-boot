package rs.ac.singidunum.pj.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.pj.entity.Ticket;
import rs.ac.singidunum.pj.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path="/api/ticket")
public class TicketController {
    
    private final TicketService service;

    @GetMapping
    public List<Ticket> getTickets() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Integer id){
        return ResponseEntity.of(service.getById(id));
    }

    @PostMapping(path = "/time-table/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createByTimeTableById(@PathVariable Integer id){
        service.createByTimeTableId(id);
    }
    

    @PutMapping(path = "/{id}/pay")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void payTicketById(@PathVariable Integer id){
        service.payById(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicketById(@PathVariable Integer id){
        service.deleteById(id);
    }
}
