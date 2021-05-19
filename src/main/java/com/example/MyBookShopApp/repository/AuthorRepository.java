package com.example.MyBookShopApp.repository;

import java.util.List;

import com.example.MyBookShopApp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("from Author")
    List<Author> findAllBooks();
}
