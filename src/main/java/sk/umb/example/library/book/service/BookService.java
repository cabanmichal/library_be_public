package sk.umb.example.library.book.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sk.umb.example.library.book.persistene.entity.BookEntity;
import sk.umb.example.library.book.persistene.repository.BookRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AtomicLong lastIndex = new AtomicLong(0);
    private final Map<Long, BookDetailDto> bookDatabase = new HashMap<>();

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<BookDetailDto> getAllBooks() {
        return mapToDtoList(bookRepository.findAll());
    }

    public List<BookDetailDto> searchBookByTitle(String title) {
        return mapToDtoList(bookRepository.findByTitle(title));
    }

    public BookDetailDto getBookById(Long id) {
        return mapToDto(getBookEntityById(id));
    }

    @Transactional
    public Long createBook(BookRequestDto bookRequestDto) {
        return bookRepository.save(mapToEntity(bookRequestDto)).getId();
    }

    @Transactional
    public void updateBook(Long bookId, BookRequestDto bookRequestDto) {
        BookEntity bookEntity = getBookEntityById(bookId);

        String title = bookRequestDto.getTitle();
        if (title != null && !title.isEmpty() && !title.isBlank()) {
            bookEntity.setTitle(bookRequestDto.getTitle());
        }

        String author = bookRequestDto.getAuthor();
        if (author != null && !author.isEmpty() && !author.isBlank()) {
            bookEntity.setAuthor(bookRequestDto.getAuthor());
        }

        String isbn = bookRequestDto.getIsbn();
        if (isbn != null && !isbn.isEmpty() && !isbn.isBlank()) {
            bookEntity.setIsbn(bookRequestDto.getIsbn());
        }

        if (bookRequestDto.getCount() != null) {
            bookEntity.setCount(bookRequestDto.getCount());
        }
        if (bookRequestDto.getCategoryIds() != null) {
            bookEntity.setCategoryIds(bookRequestDto.getCategoryIds());
        }
        if (bookRequestDto.getStatus() != null) {
            bookEntity.setStatus(bookRequestDto.getStatus());
        }

        bookRepository.save(bookEntity);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    private BookEntity mapToEntity(BookRequestDto bookRequestDto) {
        BookEntity book = new BookEntity();
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setCount(bookRequestDto.getCount());
        book.setStatus(bookRequestDto.getStatus());
        book.setCategoryIds(bookRequestDto.getCategoryIds());
        
        return book;
    }
    private List<BookDetailDto> mapToDtoList(Iterable<BookEntity> bookEntities) {
        List<BookDetailDto> books = new ArrayList<>();

        bookEntities.forEach(bookEntity -> {
            books.add(mapToDto(bookEntity));
        });

        return books;
    }

    private BookDetailDto mapToDto(BookEntity bookEntity) {
        BookDetailDto bookDetailDto = new BookDetailDto();
        bookDetailDto.setId(bookEntity.getId());
        bookDetailDto.setTitle(bookEntity.getTitle());
        bookDetailDto.setAuthor(bookEntity.getAuthor());
        bookDetailDto.setIsbn(bookEntity.getIsbn());
        bookDetailDto.setCount(bookEntity.getCount());
        bookDetailDto.setStatus(bookEntity.getStatus());
        bookDetailDto.setCategoryIds(bookEntity.getCategoryIds());
        return bookDetailDto;
    }

    private BookEntity getBookEntityById(Long bookId) {
        Optional<BookEntity> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new IllegalArgumentException("Book not found. ID: " + bookId);
        }

        return book.get();
    }
}
