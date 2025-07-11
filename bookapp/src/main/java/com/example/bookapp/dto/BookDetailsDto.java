package com.example.bookapp.dto;

import jakarta.validation.constraints.*;

import lombok.Data;
@Data
public class BookDetailsDto {
    private String id;
    private String title;
    private String author;
    private String publicationDate;
    private String isbn;
    private String genre;
    private int rating;
    private String uniqueId;
    private String coverImage;

    private String description;
    private String previewLink;
    private Integer pageCount;
}
