package rs.ac.singidunum.pj.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.pj.service.TimeTableService;
import rs.ac.singidunum.pj.entity.TimeTable;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/time-table")
public class TimeTableController {
    
    private final TimeTableService service;

    @GetMapping
    public List<TimeTable> getTimeTables() {
        return service.getAll();
    }
}
