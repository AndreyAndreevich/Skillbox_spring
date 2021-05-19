package com.example.MyBookShopApp.controllers;

import java.util.List;

import com.example.MyBookShopApp.entity.Book;
import com.example.MyBookShopApp.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api("book data api")
public class BooksRestApiController {

    private final BookService bookService;

    public BooksRestApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/by-author")
    @ApiOperation("operation get book list of bookshop by passed author first name")
    public ResponseEntity<List<Book>> booksByAuthor(@RequestParam("author") String author) {
        return ResponseEntity.ok(bookService.getBookByAuthor(author));
    }

    @ApiOperation("get list of book by title")
    @GetMapping("/books/by-title")
    public ResponseEntity<List<Book>> bookByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @ApiOperation("get list of book by range from min price to max price")
    @GetMapping("/books/by-price-range")
    public ResponseEntity<List<Book>> priceRangeBooks(
        @RequestParam("min") Integer min,
        @RequestParam("max") Integer max
    ) {
        return ResponseEntity.ok(bookService.getBookByPriceOldBetween(min, max));
    }

    @ApiOperation("get list of book with max discount")
    @GetMapping("/books/with-max-discount")
    public ResponseEntity<List<Book>> maxDiscountBooks() {
        return ResponseEntity.ok(bookService.getBooksWithMaxDiscount());
    }

    @ApiOperation("get bestseller books (which is_bestseller = 1)")
    @GetMapping("/books/bestsellers")
    public ResponseEntity<List<Book>> bestSellerBooks() {
        return ResponseEntity.ok(bookService.getBestsellers());
    }
}
