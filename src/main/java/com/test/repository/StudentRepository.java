package com.test.repository;

import com.test.model.Student;
import com.test.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student getById(int id);

    // List<Student> findAllByUniversityAndOrderByName(University university);
    @Query("select s from Student  s where s.university.id=?1 order by s.name")
    List<Student> getStudentsByYsu(int id);

    @Query("SELECT s FROM Student s WHERE   s.age = ?1 AND s.gender = ?2 AND s.name = ?3  AND s.email = ?4")
    Student getByEmail1(int age, Gender gender, String name, String email);

    @Query("select m from Student m where  upper(m.name) like concat('%', upper(?1), '%') ")
    List<Student> getByQuery(String name);

    @Query(value = "SELECT u FROM Student u WHERE u.entranceDate between ?1 AND ?2")
    List<Student> getAllBetweenDates(long fromMills, long toMills);

}