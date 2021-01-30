package com.example.MyBookShopApp.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final JdbcTemplate jdbcTemplate;
    String query = "SELECT b.*, a.name FROM books b INNER JOIN authors a ON b.authorid = a.id";

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("authorId"));
            author.setName(rs.getString("name"));

            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(author);
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));

            return book;
        });

        return new ArrayList<>(books);
    }
}
