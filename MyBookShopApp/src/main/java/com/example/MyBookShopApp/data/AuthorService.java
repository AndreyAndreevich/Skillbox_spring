package com.example.MyBookShopApp.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> getAuthors() {
        List<Author> authors = jdbcTemplate.query("SELECT * from authors", (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            author.setBiography(rs.getString("biography"));

            return author;
        });

        return new ArrayList<>(authors);
    }

    public Map<Character, List<Author>> getAuthorsByFirstLetter() {
        return getAuthors().stream().collect(Collectors.toMap(
            author -> Character.toUpperCase(author.getName().charAt(0)),
            author -> new ArrayList<>(List.of(author)),
            (oldValue, newValue) -> {
                ArrayList<Author> oldAuthorList = (ArrayList<Author>) oldValue;
                ArrayList<Author> newAuthorList = (ArrayList<Author>) newValue;
                oldAuthorList.addAll(newAuthorList);
                return oldAuthorList;
            }
        ));
    }
}
