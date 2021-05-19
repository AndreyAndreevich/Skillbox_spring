package com.example.MyBookShopApp.controllers;

import java.util.List;
import java.util.Map;

import com.example.MyBookShopApp.entity.Author;
import com.example.MyBookShopApp.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api("authors data")
public class AuthorsController {

    private final AuthorService authorService;

    @Autowired
    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap() {
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage() {
        return "/authors/index";
    }

    @GetMapping("/authors/slug")
    public String slugPage() {
        return "/authors/slug";
    }

    @GetMapping("/api/authors")
    @ApiOperation("method to get map of authors")
    @ResponseBody
    public Map<String, List<Author>> authors() {
        return authorService.getAuthorsMap();
    }
}
