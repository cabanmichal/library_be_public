package sk.umb.example.library.borrowing.service;

import sk.umb.example.library.book.service.BookDetailDto;
import sk.umb.example.library.customer.service.CustomerDetailDto;

import java.util.Date;

public class BorrowingDetailDTO {
    private Long id;
    private BookDetailDto bookDetailDTO;
    private CustomerDetailDto customerDetailDTO;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookDetailDto getBookDetailDTO() {
        return bookDetailDTO;
    }

    public void setBookDetailDTO(BookDetailDto bookDetailDTO) {
        this.bookDetailDTO = bookDetailDTO;
    }

    public CustomerDetailDto getCustomerDetailDTO() {
        return customerDetailDTO;
    }

    public void setCustomerDetailDTO(CustomerDetailDto customerDetailDTO) {
        this.customerDetailDTO = customerDetailDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
