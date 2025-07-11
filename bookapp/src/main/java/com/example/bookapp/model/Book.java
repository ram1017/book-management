package com.example.bookapp.model;

import com.example.bookapp.dto.BookDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String title;
    private String author;
    private String publicationDate;
    private String isbn;
    private String genre;
    private int rating;
    private String uniqueId;

    private String coverImage;


}
