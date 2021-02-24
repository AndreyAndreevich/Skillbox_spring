package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooksController {

    private final AuthorService authorService;

    @Autowired
    public BooksController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/books/popular")
    public String popularPage(){
        return "/books/popular";
    }

    @GetMapping("/books/recent")
    public String recentPage(){
        return "/books/recent";
    }
}
