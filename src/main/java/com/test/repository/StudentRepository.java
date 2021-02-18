package com.test.repository;

import com.test.model.Student;
import com.test.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student getById(int id);

    @Query("SELECT s FROM Student s WHERE   s.age = ?1 AND s.gender = ?2 AND s.name = ?3  AND s.email = ?4")
    Student getByEmail1(int age, Gender gender,String name,String email);
}