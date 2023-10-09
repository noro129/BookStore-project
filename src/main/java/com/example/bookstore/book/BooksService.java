package com.example.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    BooksService(BooksRepository booksRepository){
        this.booksRepository=booksRepository;
    }
    public List<Books> getBooks() {
        return booksRepository.findAll();
    }

    public void addBook(Books book) {
        booksRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        Optional<Books> bookById = booksRepository.findById(bookId);
        if(bookById.isPresent()){
            booksRepository.deleteById(bookId);
        } else {
            throw new IllegalStateException("book with id "+bookId+" does not exist.");
        }
    }

    public List<Books> getBooksByAuthor(String author) {
        return booksRepository.searchBookByAuthor(author);
    }

    public List<Books> getBooksByTitle(String title) {
        return booksRepository.searchBookByTitle(title);
    }

    public List<Books> getBooksByCategory(String category) {
        return booksRepository.searchBooksByCategory(category);
    }

    public List<Books> getBooksByTitleCategory(String title, String category) {
        return booksRepository.searchBooksByTitleCategory(title,category);
    }

    public List<Books> getBooksByTitleAuthor(String title, String author) {
        return booksRepository.searchBooksByAuthorTitle(author,title);
    }

    public List<Books> getBooksByAuthorCategory(String author, String category) {
        return booksRepository.searchBooksByAuthorCategory(author,category);
    }

    public List<Books> getBooksTitleAuthorCategory(String title, String author, String category) {
        return booksRepository.searchBooksByAuthorTitleCategory(author,title,category);
    }

    public List<Books> getBooksByAuthorOrTitle(String query) {
        return booksRepository.searchBooksByAuthorOrTitle(query);
    }
}
