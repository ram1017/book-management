package com.example.bookapp.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class BookDto {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 50)
    private String author;

    @NotBlank
    private String publicationDate;

    @NotBlank
    @Pattern(regexp = "\\d{13}", message = "ISBN must be a 13-digit number")
    private String isbn;

    @NotBlank
    private String genre;

    @Min(1)
    @Max(5)
    private int rating;

    private String coverImage;
    private String uniqueId;
}
