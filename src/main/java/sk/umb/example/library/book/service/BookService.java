package sk.umb.example.library.book.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final AtomicLong lastIndex = new AtomicLong(0);
    private final Map<Long, BookDetailDTO> bookDatabase = new HashMap<>();

    public List<BookDetailDTO> getAllBooks() {
        return new ArrayList<>(bookDatabase.values());
    }

    public List<BookDetailDTO> searchBookByTitle(String title) {
        return bookDatabase.values().stream()
                .filter(dto -> title.equals(dto.getTitle()))
                .toList();
    }

    public BookDetailDTO getBookById(Long id) {
        validateBookExists(id);

        return bookDatabase.get(id);
    }

    public Long createBook(BookRequestDTO bookRequestDTO) {
        BookDetailDTO bookDetailDTO = mapToBookDetailDTO(lastIndex.getAndIncrement(), bookRequestDTO);
        bookDatabase.put(bookDetailDTO.getId(), bookDetailDTO);

        return bookDetailDTO.getId();
    }

    private static BookDetailDTO mapToBookDetailDTO(Long index, BookRequestDTO bookRequestDTO)  {
        BookDetailDTO dto = new BookDetailDTO();
        dto.setId(index);
        dto.setAuthor(bookRequestDTO.getAuthor());
        dto.setTitle(bookRequestDTO.getTitle());
        dto.setIsbn(bookRequestDTO.getIsbn());
        dto.setCount(bookRequestDTO.getCount());
        dto.setCategoryIds(bookRequestDTO.getCategoryIds());
        dto.setStatus(bookRequestDTO.getStatus());

        return dto;
    }

    public void updateBook(Long bookId, BookRequestDTO bookRequestDTO) {
        validateBookExists(bookId);

        BookDetailDTO bookDetailDTO = bookDatabase.get(bookId);
        if (!Strings.isEmpty(bookRequestDTO.getTitle())) {
            bookDetailDTO.setTitle(bookRequestDTO.getTitle());
        }
        if (!Strings.isEmpty(bookRequestDTO.getAuthor())) {
            bookDetailDTO.setAuthor(bookRequestDTO.getAuthor());
        }
        if (!Strings.isEmpty(bookRequestDTO.getIsbn())) {
            bookDetailDTO.setIsbn(bookRequestDTO.getIsbn());
        }
        if (bookRequestDTO.getCount() != null) {
            bookDetailDTO.setCount(bookRequestDTO.getCount());
        }
        if (bookRequestDTO.getCategoryIds() != null) {
            bookDetailDTO.setCategoryIds(bookRequestDTO.getCategoryIds());
        }
        if (bookRequestDTO.getStatus() != null) {
            bookDetailDTO.setStatus(bookRequestDTO.getStatus());
        }
    }

    private void validateBookExists(Long bookId) {
        if (! bookDatabase.containsKey(bookId)) {
            throw new IllegalArgumentException("BookId: " + bookId + " does not exist!");
        }
    }

    public void deleteBook(Long bookId) {
        bookDatabase.remove(bookId);
    }
}
