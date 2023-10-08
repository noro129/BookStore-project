package com.example.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Books> getBooks(){
        return booksService.getBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Books book){
        booksService.addBook(book);
    }
}
