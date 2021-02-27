package com.test.service;

import com.test.model.Student;
import com.test.model.enums.Gender;
import com.test.util.exception.DuplicateException;

import java.util.List;

public interface StudentService {
    Student getById(int id);

    List<Student> getAll();

    void deleteById(int id);

    void save(Student student) throws DuplicateException;

    Student getByEmail1(int age, Gender gender, String name, String email);

    List<Student> getByQuery(String name);

    List<Student> getAllBetweenDates(long fromMills, long toMills);

    //List<Student> findAllByUniversityAndOrderByName(University university);

    List<Student> getStudentsByYsu(int id);
}
