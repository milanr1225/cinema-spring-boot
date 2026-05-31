package rs.ac.singidunum.pj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.pj.entity.Cinema;
import rs.ac.singidunum.pj.repo.CinemaRepository;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository repository;

    public List<Cinema> getAll() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Cinema> getById(Integer id) {
        return repository.findOneByCinemaIdAndDeletedAtIsNull(id);
    }

    public Cinema create(Cinema entity) {
        Cinema cinema = new Cinema();
        cinema.setName(entity.getName());
        cinema.setAddress(entity.getAddress());
        cinema.setCreatedAt(LocalDateTime.now());
        return repository.save(cinema);
    }

    public Cinema update(Integer id, Cinema entity) {
        Cinema cinema = getById(id).orElseThrow();
        cinema.setName(entity.getName());
        cinema.setAddress(entity.getAddress());
        cinema.setUpdatedAt(LocalDateTime.now());
        return repository.save(cinema);
    }

    public void deleteById(Integer id) {
        Cinema cinema = getById(id).orElseThrow();
        cinema.setDeletedAt(LocalDateTime.now());
        repository.save(cinema);
    }
}
