package rs.ac.singidunum.pj.controller;

import lombok.RequiredArgsConstructor;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.pj.entity.Cinema;
import rs.ac.singidunum.pj.repo.CinemaRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "/api/cinema")
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaRepository repository;

    @GetMapping
    public List<Cinema> getCinemas(){
        return repository.findAllByDeletedAtIsNull();
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable Integer id) {
        return ResponseEntity.of(repository.findOneByCinemaIdAndDeletedAtIsNull(id));
    }

    @PostMapping
    public Cinema createCinema(@RequestBody Cinema entity) {
        Cinema cinema = new Cinema();
        cinema.setName(entity.getName());
        cinema.setAddress(entity.getAddress());
        cinema.setCreatedAt(LocalDateTime.now());
        return repository.save(cinema);
    }

    @PutMapping(path ="/{id}")
    public Cinema updateCinema(@PathVariable Integer id,@RequestBody Cinema entity) {
        Cinema cinema = repository.findOneByCinemaIdAndDeletedAtIsNull(id).orElseThrow();
        cinema.setName(entity.getName());
        cinema.setAddress(entity.getAddress());
        cinema.setUpdatedAt(LocalDateTime.now());
        return repository.save(cinema);
    }
    

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCinemaById(@PathVariable Integer id) {
        Cinema cinema = repository.findOneByCinemaIdAndDeletedAtIsNull(id).orElseThrow();
        cinema.setDeletedAt(LocalDateTime.now());
        repository.save(cinema);
    }
    
}
