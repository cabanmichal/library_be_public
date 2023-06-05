package sk.umb.example.library.borrowing.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.borrowing.service.BorrowingDetailDTO;
import sk.umb.example.library.borrowing.service.BorrowingRequestDTO;
import sk.umb.example.library.borrowing.service.BorrowingService;

import java.util.List;

@RestController
public class BorrowingController {
    private final BorrowingService borrowingService;

    BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("/api/borrowings")
    public List<BorrowingDetailDTO> searchBorrowing(@RequestParam(required = false) Long customerId) {
        System.out.println("Search borrowing called. Customer ID: " + customerId);
        return (customerId == null) ? borrowingService.getAllBorrowings()
                : borrowingService.searchBorrowingByCustomer(customerId);
    }

    @GetMapping("/api/borrowings/{borrowingId}")
    public BorrowingDetailDTO getBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Get borrowing called: ID = " + borrowingId);
        return borrowingService.getBorrowingById(borrowingId);
    }

    @PostMapping("/api/borrowings")
    public Long createBorrowing(@RequestBody BorrowingRequestDTO borrowingRequestDTO) {
        System.out.println("Create borrowing called.");
        return borrowingService.createBorrowing(borrowingRequestDTO);
    }

    @PutMapping("/api/borrowings/{borrowingId}")
    public void updateBorrowing(@PathVariable Long borrowingId, @RequestBody BorrowingRequestDTO borrowingRequestDTO) {
        System.out.println("Update borrowing called: ID = " + borrowingId);
        borrowingService.updateBorrowing(borrowingId, borrowingRequestDTO);
    }

    @DeleteMapping("/api/borrowings/{borrowingId}")
    public void deleteBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Delete borrowing called: ID = " + borrowingId);
        borrowingService.deleteBorrowing(borrowingId);
    }
}

