package com.test.service;

import com.test.model.Book;
import com.test.util.exception.NotFoundException;

import java.util.List;

public interface BookService {
    Book getById(int id) throws NotFoundException;

    List<Book> getAll();

    void deleteById(int id);

    void save(Book book);
}
