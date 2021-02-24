package com.test.service;

import com.test.model.Student;
import com.test.model.University;
import com.test.repository.UniversityRepository;
import com.test.util.exception.DuplicateException;
import com.test.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    public UniversityRepository universityRepository;

    @Override
    public University getById(int id) {
        return universityRepository.getById(id);
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        universityRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void save(University university) throws DuplicateException {
        University universityFromDB = universityRepository.getByNameAndAddress(university.getName(), university.getAddress());
        if (universityFromDB != null) {
            throw new DuplicateException("duplicate");
        }
        universityRepository.save(university);
    }
    @Transactional
    public void saveTest(){
        University university = new University();
        university.setAddress("AAAA");
        university.setName("BBBBBJJJJ");
        universityRepository.save(university);
        throw new RuntimeException();
    }

    @Override
    public University getByNameAndAddress(String name, String address) {
        return universityRepository.getByNameAndAddress(name, address);
    }
    @Override
    public List <University> getByCreationDate(LocalDate creationDate){
        return universityRepository.getByCreationDate(creationDate);
    }
}
