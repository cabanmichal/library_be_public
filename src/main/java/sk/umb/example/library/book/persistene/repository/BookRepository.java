package sk.umb.example.library.book.persistene.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.book.persistene.entity.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
    Iterable<BookEntity> findByTitle(String title);
}
