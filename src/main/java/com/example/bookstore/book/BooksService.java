package com.example.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
