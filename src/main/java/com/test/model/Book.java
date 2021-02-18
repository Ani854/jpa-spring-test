package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.model.enums.BookTypes;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "book")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "num_of_page")
    private int numOfPage;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BookTypes bookTypes;

    public BookTypes getBookTypes() {
        return bookTypes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumOfPage(int numOfPage) {
        this.numOfPage = numOfPage;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setBookTypes(BookTypes bookTypes) {
        this.bookTypes = bookTypes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumOfPage() {
        return numOfPage;
    }

    public Student getStudent() {
        return student;
    }

}
