package org.example.app.services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> retreiveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", book.getAuthor());
        parameterSource.addValue("title", book.getTitle());
        parameterSource.addValue("size", book.getSize());
        jdbcTemplate.update(
            "INSERT INTO books(author,title,size) VALUES(:author, :title, :size)",
            parameterSource
        );
        logger.info("store new book: " + book);
    }

    @Override
    public boolean remove(Book bookToRemove) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String query = "DELETE FROM books WHERE";

        int mapCounter = 0;

        if (bookToRemove.getId() != null) {
            parameterSource.addValue("id", bookToRemove.getId());
            query += " id = :id";
            mapCounter++;
        }

        if (!bookToRemove.getAuthor().isEmpty()) {
            parameterSource.addValue("author", bookToRemove.getAuthor());
            query = updateQuery(mapCounter, query);
            query += " author = :author";
            mapCounter++;
        }

        if (!bookToRemove.getTitle().isEmpty()) {
            parameterSource.addValue("title", bookToRemove.getTitle());
            query = updateQuery(mapCounter, query);
            query += " title = :title";
            mapCounter++;
        }

        if (bookToRemove.getSize() != null) {
            parameterSource.addValue("size", bookToRemove.getSize());
            query = updateQuery(mapCounter, query);
            query += " size = :size";
        }

        jdbcTemplate.update(query, parameterSource);

        logger.info("remove book completed");

        return true;
    }

    private String updateQuery(int counter, String query) {
        if (counter > 0) {
            return query + " AND";
        }
        return query;
    }
}
