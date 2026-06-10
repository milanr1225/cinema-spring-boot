package rs.ac.singidunum.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.pj.entity.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
    
    List<TimeTable> findAllByDeletedAtIsNull();
}
