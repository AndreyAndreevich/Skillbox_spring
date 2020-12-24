package org.example.web.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.example.web.dto.BookToRemove;
import org.example.web.validation.SaveGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private final Logger logger = Logger.getLogger(BookShelfController.class);
    private final BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model, String author, String title, Integer size) {
        logger.info("got book shelf");
        logger.info("filter books by" + author + " " + title + " " + size);

        List<Book> books = bookService.getAllBooks();
        Stream<Book> stream = books.stream();

        if (author != null && !author.isEmpty()) {
            stream = stream.filter(book -> book.getAuthor().startsWith(author));
        }
        if (title != null && !title.isEmpty()) {
            stream = stream.filter(book -> book.getTitle().startsWith(title));
        }
        if (size != null) {
            stream = stream.filter(book -> book.getSize().equals(size));
        }

        model.addAttribute("book", new Book());
        model.addAttribute("bookToRemove", new BookToRemove());
        model.addAttribute("bookList", stream.collect(Collectors.toList()));

        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Validated(SaveGroup.class) Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.warn(result.getAllErrors());

            model.addAttribute("bookToRemove", new BookToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());

            return "book_shelf";
        }

        bookService.saveBook(book);
        logger.info("current repository size: " + bookService.getAllBooks().size());

        return "redirect:/books/shelf";
    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookToRemove bookToRemove, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.warn(result.getAllErrors());

            model.addAttribute("book", new Book());
            model.addAttribute("bookList", bookService.getAllBooks());

            return "book_shelf";
        }

        if (bookToRemove.getId() == null && bookToRemove.getAuthor().isEmpty() &&
            bookToRemove.getTitle().isEmpty() && bookToRemove.getSize() == null) {
            logger.warn("empty book");
        } else if (!bookService.remove(bookToRemove)) {
            logger.warn("book: " + bookToRemove + " not found:");
        }

        return "redirect:/books/shelf";
    }
}
