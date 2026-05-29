package rs.ac.singidunum.pj.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.pj.entity.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

  List<Cinema> findAllByDeletedAtIsNull();

  Optional<Cinema> findOneByCinemaIdAndDeletedAtIsNull(Integer id);
}
