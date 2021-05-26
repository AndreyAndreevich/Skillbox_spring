package com.example.MyBookShopApp.data;

import java.util.List;

import com.example.MyBookShopApp.entity.Book;
import lombok.Data;

@Data
public class BooksPageDto {
    private Integer count;
    private List<Book> books;

    public BooksPageDto(List<Book> books) {
        this.books = books;
        this.count = books.size();
    }
}
