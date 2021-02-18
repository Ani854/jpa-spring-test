package com.test.service;

import com.test.model.Book;
import com.test.repository.BooksRepository;
import com.test.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BooksRepository booksRepository;

    @Override
    public Book getById(int id) throws NotFoundException {
        Book book = booksRepository.getById(id);
        if (book == null) {
            throw new NotFoundException("could not found book whit current id " + id);
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        return booksRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    @Override
    public void save(Book book) {
        booksRepository.save(book);
    }
}
