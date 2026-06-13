package rs.ac.singidunum.pj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.pj.entity.Ticket;
import rs.ac.singidunum.pj.entity.TimeTable;
import rs.ac.singidunum.pj.model.MovieModel;
import rs.ac.singidunum.pj.repo.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;
    private final MovieService movieService;
    private final TimeTableService timeTableService;

    public List<Ticket> getAll() {
        List<Ticket> list = repository.findAllByDeletedAtIsNull();
        List<Integer> ids = list.stream().map(obj -> obj.getTimeTable().getMovieId()).toList();
        List<MovieModel> movies = movieService.getByIds(ids);

        for (Ticket t : list) {
            TimeTable tt = t.getTimeTable();

            for (MovieModel movie : movies) {
                if (Objects.equals(tt.getMovieId(), movie.getMovieId())) {
                    tt.setMovie(movie);
                }
            }

            t.setTimeTable(tt);
        }

        return list;
    }

    public Optional<Ticket> getById(Integer id) {
        Optional<Ticket> ticket = repository.findOneByTicketIdAndDeletedAtIsNull(id);

        if (ticket.isEmpty()) {
            return Optional.empty();
        }

        TimeTable timeTable = ticket.get().getTimeTable();
        Optional<MovieModel> movie = movieService.getById(timeTable.getMovieId());

        if (movie.isPresent()) {
            timeTable.setMovie(movie.get());
        }

        ticket.get().setTimeTable(timeTable);
        return Optional.of(ticket.get());
    }

    public void createByTimeTableId(Integer id) {
        TimeTable timeTable = timeTableService.getById(id).orElseThrow();

        Ticket ticket = new Ticket();
        ticket.setTimeTable(timeTable);
        ticket.setCreatedAt(LocalDateTime.now());
        repository.save(ticket);
    }

    public void payById(Integer id) {
        Ticket ticket = repository.findOneByTicketIdAndDeletedAtIsNull(id).orElseThrow();
        ticket.setPaidAt(LocalDateTime.now());
        repository.save(ticket);
    }

    public void deleteById(Integer id) {
        Ticket ticket = repository.findOneByTicketIdAndDeletedAtIsNull(id).orElseThrow();
        if (ticket.getPaidAt() != null)
            throw new RuntimeException("TICKET_HAS_BEEN_PAID");

        ticket.setDeletedAt(LocalDateTime.now());
        repository.save(ticket);
    }
}