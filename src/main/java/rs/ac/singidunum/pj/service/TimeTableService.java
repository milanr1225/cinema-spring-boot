package rs.ac.singidunum.pj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.pj.repo.TimeTableRepository;
import rs.ac.singidunum.pj.entity.TimeTable;
import rs.ac.singidunum.pj.model.MovieModel;

@Service
@RequiredArgsConstructor
public class TimeTableService {
    private final TimeTableRepository repository;
    private final MovieService movieService;
    private final CinemaService cinemaService;

    public List<TimeTable> getAll() {
        List<TimeTable> list = repository.findAllByDeletedAtIsNull();
        List<Integer> ids = list.stream().map(obj -> obj.getMovieId()).toList();
        List<MovieModel> movies = movieService.getByIds(ids);

        for(TimeTable timeTable : list) {
            for (MovieModel movie : movies) {
                if (Objects.equals(timeTable.getMovieId(), movie.getMovieId())) {
                    timeTable.setMovie(movie);
                }
            }
        }

        

        return list;
    }

    public Optional<TimeTable> getById(Integer id) {
        return repository.findOneByTimeTableIdAndDeletedAtIsNull(id);
    }

    public TimeTable create(TimeTable entity) {
        TimeTable timeTable = new TimeTable();
        timeTable.setMovieId(entity.getMovieId());
        timeTable.setCinema(cinemaService.getById(entity.getCinema().getCinemaId()).orElse(null));
        timeTable.setTimeStart(entity.getTimeStart());
        timeTable.setCreatedAt(LocalDateTime.now());
        return repository.save(timeTable);
    }

    public TimeTable update(Integer id, TimeTable entity) {
        TimeTable timeTable = getById(id).orElseThrow();
        timeTable.setMovieId(entity.getMovieId());
        timeTable.setCinema(cinemaService.getById(entity.getCinema().getCinemaId()).orElse(null));
        timeTable.setTimeStart(entity.getTimeStart());
        timeTable.setUpdatedAt(LocalDateTime.now());
        return repository.save(timeTable);
    }

    public void deleteById(Integer id) {
        TimeTable timeTable = getById(id).orElseThrow();
        timeTable.setDeletedAt(LocalDateTime.now());
        repository.save(timeTable);
    }
}
