package rs.ac.singidunum.pj.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.pj.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    List<Ticket> findAllByDeletedAtIsNull();

    Optional<Ticket> findOneByTicketIdAndDeletedAtIsNull(Integer id);
}
