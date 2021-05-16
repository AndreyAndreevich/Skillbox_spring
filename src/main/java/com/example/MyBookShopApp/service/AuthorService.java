package com.example.MyBookShopApp.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.MyBookShopApp.entity.Author;
import com.example.MyBookShopApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<Author>> getAuthorsMap() {
        return authorRepository.findAllBooks()
            .stream()
            .collect(
                Collectors.groupingBy(
                    author -> author.getLastName().substring(0, 1)
                )
            );
    }
}
