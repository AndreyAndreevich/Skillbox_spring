package org.example.app.repository;

import java.util.List;

import org.example.web.dto.Book;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean remove(Book bookToRemove);
}
