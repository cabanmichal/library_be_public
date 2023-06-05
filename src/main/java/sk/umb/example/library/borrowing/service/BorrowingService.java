package sk.umb.example.library.borrowing.service;

import org.springframework.stereotype.Service;
import sk.umb.example.library.book.service.BookService;
import sk.umb.example.library.customer.service.CustomerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BorrowingService {
    private final AtomicLong lastIndex = new AtomicLong(0);
    private final Map<Long, BorrowingDetailDTO> borrowingDatabase = new HashMap<>();
    private final BookService bookService;
    private final CustomerService customerService;

    BorrowingService(BookService bookService, CustomerService customerService) {
        this.bookService = bookService;
        this.customerService = customerService;
    }

    public List<BorrowingDetailDTO> getAllBorrowings() {
        return new ArrayList<>(borrowingDatabase.values());
    }

    public List<BorrowingDetailDTO> searchBorrowingByCustomer(Long customerId) {
        return borrowingDatabase.values().stream()
                .filter(dto -> customerId.equals(dto.getCustomerDetailDTO().getId()))
                .toList();
    }

    public BorrowingDetailDTO getBorrowingById(Long borrowingId) {
        validateBorrowingExists(borrowingId);

        return borrowingDatabase.get(borrowingId);
    }

    public Long createBorrowing(BorrowingRequestDTO borrowingRequestDTO) {
        BorrowingDetailDTO borrowingDetailDTO = mapToBorrowingDetailDTO(lastIndex.getAndIncrement(), borrowingRequestDTO);
        borrowingDatabase.put(borrowingDetailDTO.getId(), borrowingDetailDTO);

        return borrowingDetailDTO.getId();
    }

    private BorrowingDetailDTO mapToBorrowingDetailDTO(Long index, BorrowingRequestDTO borrowingRequestDTO)  {
        BorrowingDetailDTO dto = new BorrowingDetailDTO();
        dto.setId(index);
        dto.setBookDetailDTO(bookService.getBookById(borrowingRequestDTO.getBookId()));
        dto.setCustomerDetailDTO(customerService.getCustomerById(borrowingRequestDTO.getCustomerId()));
        dto.setDate(new Date());

        return dto;
    }

    public void updateBorrowing(Long borrowingId, BorrowingRequestDTO borrowingRequestDTO) {
        validateBorrowingExists(borrowingId);

        BorrowingDetailDTO borrowingDetailDTO = borrowingDatabase.get(borrowingId);

        if (borrowingRequestDTO.getBookId() != null) {
            borrowingDetailDTO.setBookDetailDTO(bookService.getBookById(borrowingRequestDTO.getBookId()));
        }
        if (borrowingRequestDTO.getCustomerId() != null) {
            borrowingDetailDTO.setCustomerDetailDTO(
                    customerService.getCustomerById(borrowingRequestDTO.getCustomerId())
            );
        }
    }

    private void validateBorrowingExists(Long borrowingId) {
        if (! borrowingDatabase.containsKey(borrowingId)) {
            throw new IllegalArgumentException("BorrowingId: " + borrowingId + " does not exist!");
        }
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingDatabase.remove(borrowingId);
    }
}
