package com.example.MyBookShopApp.data;

import java.util.List;

import com.example.MyBookShopApp.entity.Book;
import lombok.Data;

@Data
public class RecommendedBooksPageDto {
    private Integer count;
    private List<Book> books;

    public RecommendedBooksPageDto(List<Book> books) {
        this.books = books;
        this.count = books.size();
    }
}
