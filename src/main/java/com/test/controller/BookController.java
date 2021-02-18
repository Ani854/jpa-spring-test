package com.test.controller;

import com.test.model.Book;
import com.test.service.BookService;
import com.test.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    BookService booksService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable int id) throws NotFoundException {
        Book book = booksService.getById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> bookList = booksService.getAll();
        return ResponseEntity.ok(bookList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        booksService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void save( @Valid @RequestBody Book book) {
        booksService.save(book);
    }
}
