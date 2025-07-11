package com.example.bookapp.repository;

import com.example.bookapp.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findFirstByOrderByUniqueIdDesc();
    Optional<Book> findByUniqueId(String uniqueId);

}
