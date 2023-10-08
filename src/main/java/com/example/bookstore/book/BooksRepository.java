package com.example.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Books,Long> {

    @Query("SELECT b FROM Books b WHERE b.title=?1")
    Optional<Books> findBookByTitle(String title);

    @Query("SELECT b FROM Books b WHERE b.author=?1")
    Optional<Books> findBookByAuthor(String author);
}
