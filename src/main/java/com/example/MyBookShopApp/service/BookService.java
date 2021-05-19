package com.example.MyBookShopApp.service;

import java.util.List;

import com.example.MyBookShopApp.entity.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    public List<Book> getBookByAuthor(String authorFirstName) {
        return bookRepository.findBookByAuthorFirstNameContaining(authorFirstName);
    }

    public List<Book> getBookByTitle(String bookTitle) {
        return bookRepository.findBookByTitleContaining(bookTitle);
    }

    public List<Book> getBookByPriceOldBetween(Integer min, Integer max) {
        return bookRepository.findBookByPriceOldBetween(min, max);
    }

    public List<Book> getBookByPriceOldIs(Integer price) {
        return bookRepository.findBookByPriceOldIs(price);
    }

    public List<Book> getBestsellers() {
        return bookRepository.getBestsellers();
    }

    public List<Book> getBooksWithMaxDiscount() {
        return bookRepository.getBooksWithMaxDiscount();
    }
}
