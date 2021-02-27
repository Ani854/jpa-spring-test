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

    @GetMapping("/search-by-name")
    public List<Student> getAllByQuery(@RequestParam(value = "name") String name) {
        return studentService.getByQuery(name);
    }

    @GetMapping("/get-by-entrance-date")
    public List<Student> getAllBetweenDates(@RequestParam long fromMills, @RequestParam long toMills) {
        return studentService.getAllBetweenDates(fromMills, toMills);
    }

    // @GetMapping("/get-students-by-university")
    //public List<Student> findAllByUniversityAndOrderByName(University university) {
    //  return studentService.findAllByUniversityAndOrderByName(university);
    //}
    @GetMapping("/get-by-ysu")
    public List<Student> getByYsu(@RequestParam int id) {
        List<Student> studentList = studentService.getStudentsByYsu(id);
        return studentList;
    }
}