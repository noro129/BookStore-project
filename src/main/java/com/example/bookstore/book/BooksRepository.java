package com.example.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BooksRepository extends JpaRepository<Books,Long> {

    @Query("SELECT b FROM Books b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%',?1,'%'))")
    List<Books> searchBookByTitle(String title);

    @Query("SELECT b FROM Books b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%',?1,'%'))")
    List<Books> searchBookByAuthor(String author);

    @Query("SELECT b FROM Books b WHERE b.category = ?1")
    List<Books> searchBooksByCategory(String category);

    @Query("SELECT b FROM Books b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%',?1,'%')) OR LOWER(b.title) LIKE LOWER(CONCAT('%',?1,'%'))")
    List<Books> searchBooksByAuthorOrTitle(String search);

    @Query("SELECT b FROM Books b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%',?1,'%')) AND LOWER(b.title) LIKE LOWER(CONCAT('%',?2,'%'))")
    List<Books> searchBooksByAuthorTitle(String author,String title);

    @Query("SELECT b FROM Books b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%',?1,'%')) AND b.category LIKE CONCAT('%',?2,'%')")
    List<Books> searchBooksByAuthorCategory(String author,String category);

    @Query("SELECT b FROM Books b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%',?1,'%')) AND b.category LIKE CONCAT('%',?2,'%')")
    List<Books> searchBooksByTitleCategory(String title,String category);

    @Query("SELECT b FROM Books b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%',?1,'%')) AND LOWER(b.title) LIKE LOWER(CONCAT('%',?2,'%')) AND b.category LIKE CONCAT('%',?3,'%')")
    List<Books> searchBooksByAuthorTitleCategory(String author, String title,String category);
}
