package rs.ac.singidunum.pj.controller;

import lombok.RequiredArgsConstructor;

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


@RestController
@RequestMapping(path = "/api/cinema")
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaRepository repository;

    @GetMapping
    public List<Cinema> getCinemas(){
        return repository.findAllByDeletedAtIsNull();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCinemaById(@PathVariable Integer id) {
        Cinema cinema = repository.findOneByCinemaIdAndDeletedAtIsNull(id).orElseThrow();
        cinema.setDeletedAt(LocalDateTime.now());
        repository.save(cinema);
    }
    
}
