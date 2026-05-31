package rs.ac.singidunum.pj.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.pj.entity.Cinema;
import rs.ac.singidunum.pj.service.CinemaService;

import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "/api/cinema")
@CrossOrigin
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaService service;

    @GetMapping
    public List<Cinema> getCinemas(){
        return service.getAll();
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getById(id));
    }

    @PostMapping
    public Cinema createCinema(@RequestBody Cinema entity) {
        return service.create(entity);
    }

    @PutMapping(path ="/{id}")
    public Cinema updateCinema(@PathVariable Integer id,@RequestBody Cinema entity) {
        return service.update(id, entity);
    }
    

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCinemaById(@PathVariable Integer id) {
        service.deleteById(id);
    }
    
}
