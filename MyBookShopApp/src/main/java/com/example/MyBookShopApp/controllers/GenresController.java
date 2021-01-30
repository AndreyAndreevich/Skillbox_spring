package com.example.MyBookShopApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop/genres")
public class GenresController {

    @Autowired
    public GenresController() {
    }

    @GetMapping()
    public String genres() {
        return "genres/index";
    }

    @GetMapping("/slug")
    public String genresSlug() {
        return "genres/slug";
    }
}
