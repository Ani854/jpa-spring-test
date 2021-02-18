package com.test.controller;

import com.test.model.University;
import com.test.service.UniversityService;
import com.test.util.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/university")
public class UniversityController {
    @Autowired

    private UniversityService universityService;

    @GetMapping("/{id}")
    public ResponseEntity<University> getById(@PathVariable int id) {
        University university = universityService.getById(id);
        return ResponseEntity.ok(university);
    }

    @GetMapping
    public ResponseEntity<List<University>> getAll() {
        List<University> universityList = universityService.getAll();
        return ResponseEntity.ok(universityList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        universityService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void save(@RequestBody University university) throws DuplicateException {
        universityService.save(university);
    }
    @PostMapping(value = "/test")
    public void saveTest(){
        universityService.saveTest();
    }
}
