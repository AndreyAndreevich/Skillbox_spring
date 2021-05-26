package com.example.MyBookShopApp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.MyBookShopApp.data.BooksPageDto;
import com.example.MyBookShopApp.data.SearchWordDto;
import com.example.MyBookShopApp.entity.Book;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/postponed")
    public String postponedPage() {
        return "postponed";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/signin")
    public String signinPage() {
        return "signin";
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(
        @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
        Model model
    ) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute(
            "searchResults",
            bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent()
        );
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(
        @RequestParam("offset") Integer offset,
        @RequestParam("limit") Integer limit,
        @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto
    ) {
        return new BooksPageDto(
            bookService.getPageOfSearchResultBooks(
                searchWordDto.getExample(),
                offset,
                limit
            ).getContent());
    }
}
