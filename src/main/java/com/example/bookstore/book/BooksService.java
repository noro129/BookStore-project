package com.example.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

    @Transactional
    public void updateBook(Long bookId, String title, String author, int numPages, LocalDate publicationDate,
                           double price, String description, String category, int quantity) {
        Books bookById = booksRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("book with id "+bookId+" does not exist."));
        if(title!=null && !title.isEmpty() && !Objects.equals(title,bookById.getTitle())){
            bookById.setTitle(title);
        }
        if(author!=null && !author.isEmpty() && !Objects.equals(author,bookById.getAuthor())){
            bookById.setAuthor(author);
        }
        if(numPages!=0 && numPages!=bookById.getNumPages()){
            bookById.setNumPages(numPages);
        }
        if(publicationDate!=null && Objects.equals(publicationDate,bookById.getPublicationDate())){
            bookById.setPublicationDate(publicationDate);
        }
        if(price!=bookById.getPrice()){
            bookById.setPrice(price);
        }
        if(description!=null && !description.isEmpty() && !Objects.equals(description,bookById.getDescription())){
            bookById.setDescription(description);
        }
        if(category!=null && !category.isEmpty() && !Objects.equals(category,bookById.getCategory())){
            bookById.setCategory(category);
        }
        if(quantity!=0 && quantity!=bookById.getQuantity()){
            bookById.setQuantity(quantity);
        }
    }
}
