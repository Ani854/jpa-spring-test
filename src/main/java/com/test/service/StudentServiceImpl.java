package com.test.service;

import com.test.model.Student;
import com.test.model.enums.Gender;
import com.test.repository.StudentRepository;
import com.test.util.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public StudentRepository studentRepository;

    @Override
    public Student getById(int id) {
        return studentRepository.getById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void save(Student student) throws DuplicateException {
        Student studentFromDB = studentRepository.getByEmail1(student.getAge(), student.getGender(), student.getName(), student.getEmail());
        if (studentFromDB != null) {
            throw new DuplicateException("duplicate");
        }
        studentRepository.save(student);
    }

    @Override
    public Student getByEmail1(int age, Gender gender, String name, String email) {
        return studentRepository.getByEmail1(age, gender, name, email);
    }
}
