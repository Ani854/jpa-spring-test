package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.model.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;
    @JsonIgnore
    @JoinColumn(name = "university_id")
    @ManyToOne
    private University university;

    @OneToOne(mappedBy = "student")
    private Book book;


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public Book getBook() {
        return book;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}