package sk.umb.example.library.book.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @GetMapping("/api/books")
    public void searchBook(@RequestParam(required = false) String title) {
        System.out.println("Search book called. Title: " + title);
    }

    @GetMapping("/api/books/{bookId}")
    public void getBook(@PathVariable Long bookId) {
        System.out.println("Get book called: ID = " + bookId);
    }

    @PostMapping("/api/books")
    public void createBook() {
        System.out.println("Create book called.");
    }

    @PutMapping("/api/books/{bookId}")
    public void updateBook(@PathVariable Long bookId) {
        System.out.println("Update book called: ID = " + bookId);
    }

    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        System.out.println("Delete book called: ID = " + bookId);
    }
}
