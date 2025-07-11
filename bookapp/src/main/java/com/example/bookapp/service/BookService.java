package com.example.bookapp.service;

import com.example.bookapp.dto.BookDetailsDto;
import com.example.bookapp.dto.BookDto;
import com.example.bookapp.model.Book;
import com.example.bookapp.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private GoogleBooksService googleBooksService;

    public BookDetailsDto getBookDetailsByUniqueId(String uniqueId) {
        Book book = bookRepository.findByUniqueId(uniqueId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        BookDetailsDto dto = new BookDetailsDto();
        BeanUtils.copyProperties(book, dto);

        Map<String, Object> apiResponse = googleBooksService.fetchDetailsByISBN(book.getIsbn());

        if (apiResponse != null && apiResponse.containsKey("items")) {
            List items = (List) apiResponse.get("items");
            if (!items.isEmpty()) {
                Map volumeInfo = (Map) ((Map) items.get(0)).get("volumeInfo");
                dto.setDescription((String) volumeInfo.get("description"));
                dto.setPreviewLink((String) volumeInfo.get("previewLink"));
                dto.setPageCount((Integer) volumeInfo.get("pageCount"));


            }
        }

        return dto;
    }

    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(book -> {
            BookDto dto = new BookDto();
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setGenre(book.getGenre());
            dto.setUniqueId(book.getUniqueId());
            return dto;
        }).collect(Collectors.toList());
    }


    public Book save(BookDto bookDto) {
        String uniqueId = generateNextUniqueId();

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setIsbn(bookDto.getIsbn());
        book.setGenre(bookDto.getGenre());
        book.setRating(bookDto.getRating());
        book.setCoverImage(bookDto.getCoverImage());
        book.setUniqueId(uniqueId);

        return bookRepository.save(book);

    }

    private String generateNextUniqueId() {
        Optional<Book> latest = bookRepository.findFirstByOrderByUniqueIdDesc();

        if (latest.isPresent()) {
            String lastId = latest.get().getUniqueId();
            int lastNum = Integer.parseInt(lastId.substring(2));
            int newNum = lastNum + 1;
            return String.format("B-%03d", newNum);
        } else {
            return "B-001";
        }
    }

    public void deleteBook(String uniqueId) {
        Optional<Book> book = bookRepository.findByUniqueId(uniqueId);
        book.ifPresent(bookRepository::delete);
    }

}
