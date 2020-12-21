package org.example.app.services;

import java.util.List;

import org.example.web.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final ProjectRepository<Book> bookRepo;

    @Autowired
    public BookService(ProjectRepository<Book> bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.retreiveAll();
    }

    public void saveBook(Book book) {
        bookRepo.store(book);
    }

    public boolean remove(Book book) {
        return bookRepo.remove(el -> {
            if (book.getId() != null && !book.getId().equals(el.getId())) {
                return false;
            }
            if (!book.getAuthor().isEmpty() && !book.getAuthor().equals(el.getAuthor())) {
                return false;
            }
            if (!book.getTitle().isEmpty() && !book.getTitle().equals(el.getTitle())) {
                return false;
            }
            return book.getSize() == null || book.getSize().equals(el.getSize());
        });
    }
}
