package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenresController {

    private final AuthorService authorService;

    @Autowired
    public GenresController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/genres")
    public String genresPage() {
        return "/genres/index";
    }
}
