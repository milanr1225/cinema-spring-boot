package rs.ac.singidunum.pj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.pj.entity.Ticket;
import rs.ac.singidunum.pj.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
    
}
