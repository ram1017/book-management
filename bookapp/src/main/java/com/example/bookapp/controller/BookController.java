package com.example.bookapp.controller;

import com.example.bookapp.dto.BookDetailsDto;
import com.example.bookapp.dto.BookDto;
import com.example.bookapp.model.Book;
import com.example.bookapp.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/createbook")
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookDto bookdto) {
        Book saved =bookService.save(bookdto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
       List<BookDto> bookDtos= bookService.getBooks();
       return bookDtos;

    }

    @DeleteMapping("/books/{uniqueId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("uniqueId") String uniqueId) {
        bookService.deleteBook(uniqueId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/books/{uniqueId}")
    public ResponseEntity<BookDetailsDto> getBookWithDetails(@PathVariable String uniqueId) {
        BookDetailsDto bookDetails = bookService.getBookDetailsByUniqueId(uniqueId);
        return ResponseEntity.ok(bookDetails);
    }

}
