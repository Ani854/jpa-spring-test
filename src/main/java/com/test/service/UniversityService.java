package com.test.service;

import com.test.model.University;
import com.test.util.exception.DuplicateException;

import java.time.LocalDate;
import java.util.List;

public interface UniversityService {
    University getById(int id);

    List<University> getAll();

    void deleteById(int id);

    void save(University university) throws DuplicateException;

    University getByNameAndAddress(String name, String address);

    void saveTest() throws RuntimeException;

    List<University> getByCreationDate(LocalDate creationDate);

}