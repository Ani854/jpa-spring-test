package com.test.controller;

import com.test.model.Student;
import com.test.service.StudentService;
import com.test.util.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable int id) {
        Student student = studentService.getById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentService.getAll();
        return ResponseEntity.ok(studentList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void save(@RequestBody Student student) throws DuplicateException {
        studentService.save(student);
    }
}