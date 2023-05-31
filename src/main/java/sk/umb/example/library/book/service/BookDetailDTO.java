package sk.umb.example.library.book.service;

import java.util.ArrayList;

public class BookDetailDTO {
    private Long id;
    private String author;
    private String title;
    private String isbn;
    private Integer count;
    private ArrayList<Long> categoryIds;
    private BookStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(ArrayList<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
