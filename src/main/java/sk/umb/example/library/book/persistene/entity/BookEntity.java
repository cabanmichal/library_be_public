package sk.umb.example.library.book.persistene.entity;

import jakarta.persistence.*;
import sk.umb.example.library.book.service.BookStatus;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;

import java.util.List;
import java.util.Set;

@Entity(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String title;
    private String isbn;
    private Integer count;
    private List<Long> categoryIds;
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

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @ManyToMany
    @JoinTable(name="category_book",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private Set<CategoryEntity> categories;
}
