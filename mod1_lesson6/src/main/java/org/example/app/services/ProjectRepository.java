package org.example.app.services;

import java.util.List;
import java.util.function.Predicate;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean remove(Predicate<T> predicate);
}
