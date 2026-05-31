package rs.ac.singidunum.pj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.pj.model.MovieModel;
import rs.ac.singidunum.pj.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path="/api/movie")
public class MovieController {
    
    private final MovieService service;

    @GetMapping
    public List<MovieModel> getMovies() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieModel> getMoviesById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getById(id));
    }
  
}
