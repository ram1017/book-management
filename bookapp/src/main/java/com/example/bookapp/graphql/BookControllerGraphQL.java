package com.example.bookapp.graphql;

import com.example.bookapp.dto.BookDetailsDto;
import com.example.bookapp.dto.BookDto;
import com.example.bookapp.service.BookService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookControllerGraphQL {

    private final BookService bookService;

    public BookControllerGraphQL(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<BookDto> allBooks() {
        return bookService.getBooks();
    }

    @QueryMapping
    public BookDetailsDto bookByUniqueId(@Argument String uniqueId) {
        return bookService.getBookDetailsByUniqueId(uniqueId);
    }

    @MutationMapping
    public Boolean addBook(@Argument String title,
                           @Argument String author,
                           @Argument String publicationDate,
                           @Argument String isbn,
                           @Argument String genre,
                           @Argument int rating,
                           @Argument String coverImage) {
        BookDto dto = new BookDto();
        dto.setTitle(title);
        dto.setAuthor(author);
        dto.setPublicationDate(publicationDate);
        dto.setIsbn(isbn);
        dto.setGenre(genre);
        dto.setRating(rating);
        dto.setCoverImage(coverImage);
        bookService.save(dto);
        return true;
    }

    @MutationMapping
    public String deleteBook(@Argument String uniqueId) {
        bookService.deleteBook(uniqueId);
        return "Deleted";
    }
}
