package com.example.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "books")
public class BooksController {

    private final BooksService booksService;

    @Autowired
    BooksController(BooksService booksService){
        this.booksService=booksService;
    }

    @GetMapping
    public List<Books> getBooks(@RequestParam(required = false) String category,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) String author){
        if (category==null && title==null && author==null) return booksService.getBooks();
        else if (category==null && title==null) return booksService.getBooksByAuthor(author);
        else if (category==null && author==null) return booksService.getBooksByTitle(title);
        else if (author==null && title==null) return booksService.getBooksByCategory(category);
        else if (author==null) return booksService.getBooksByTitleCategory(title,category);
        else if (category==null) return booksService.getBooksByTitleAuthor(title,author);
        else if (title==null) return booksService.getBooksByAuthorCategory(author,category);
        else return booksService.getBooksTitleAuthorCategory(title,author,category);
    }

    @GetMapping(path = "search")
    public List<Books> searchBooks(@RequestParam(required = false) String query){
        if(query==null || query.isEmpty()) return Collections.emptyList();
        return booksService.getBooksByAuthorOrTitle(query);
    }

    @PostMapping
    public void addBook(@RequestBody Books book){
        booksService.addBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId){
        booksService.deleteBook(bookId);
    }

    @PutMapping("{bookId}")
    public void updateBook(@PathVariable("bookId") Long bookId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String author,
                           @RequestParam(required = false) int numPages,
                           @RequestParam(required = false) LocalDate publicationDate,
                           @RequestParam(required = false) double price,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) String category,
                           @RequestParam(required = false) int quantity){
        booksService.updateBook(bookId, title, author, numPages, publicationDate, price, description, category, quantity);
    }
}
