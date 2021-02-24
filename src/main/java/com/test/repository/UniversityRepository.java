package com.test.repository;

import com.test.model.Student;
import com.test.model.University;
import com.test.util.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    University getById(int id);

    @Query("SELECT u FROM University u WHERE u.name = ?1 AND u.address = ?2")
    University getByNameAndAddress(String name, String address);

    @Query("SELECT u FROM University u WHERE u.creationDate < ?1 ")
    List <University> getByCreationDate(LocalDate creationDate);
}
