package sk.umb.example.library.book.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.book.service.BookRequestDTO;
import sk.umb.example.library.book.service.BookService;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<BookDetailDTO> searchBook(@RequestParam(required = false) String title) {
        System.out.println("Search book called. Title: " + title);

        return Strings.isEmpty(title) ? bookService.getAllBooks()
                : bookService.searchBookByTitle(title);
    }

    @GetMapping("/api/books/{bookId}")
    public BookDetailDTO getBook(@PathVariable Long bookId) {

        System.out.println("Get book called: ID = " + bookId);
        return bookService.getBookById(bookId);
    }

    @PostMapping("/api/books")
    public Long createBook(@RequestBody BookRequestDTO bookRequestDTO) {

        System.out.println("Create book called.");
        return bookService.createBook(bookRequestDTO);
    }

    @PutMapping("/api/books/{bookId}")
    public void updateBook(@PathVariable Long bookId, @RequestBody BookRequestDTO bookRequestDTO) {
        System.out.println("Update book called: ID = " + bookId);
        bookService.updateBook(bookId, bookRequestDTO);
    }

    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        System.out.println("Delete book called: ID = " + bookId);
        bookService.deleteBook(bookId);
    }
}
