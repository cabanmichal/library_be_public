package sk.umb.example.library.borrowing.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowingController {
    @GetMapping("/api/borrowings")
    public void searchBorrowing(@RequestParam(required = false) String title) {
        System.out.println("Search borrowing called. Title: " + title);
    }

    @GetMapping("/api/borrowings/{borrowingId}")
    public void getBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Get borrowing called: ID = " + borrowingId);
    }

    @PostMapping("/api/borrowings")
    public void createBorrowing() {
        System.out.println("Create borrowing called.");
    }

    @PutMapping("/api/borrowings/{borrowingId}")
    public void updateBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Update borrowing called: ID = " + borrowingId);
    }

    @DeleteMapping("/api/borrowings/{borrowingId}")
    public void deleteBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Delete borrowing called: ID = " + borrowingId);
    }
}

