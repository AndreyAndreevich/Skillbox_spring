package com.example.MyBookShopApp.repository;

import java.util.List;

import com.example.MyBookShopApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthor_FirstName(String name);

    @Query("from Book")
    List<Book> customFindAllBooks();

    List<Book> findBookByAuthorFirstNameContaining(String authorFirstName);

    List<Book> findBookByTitleContaining(String bookTitle);

    List<Book> findBookByPriceOldBetween(Integer min, Integer max);

    List<Book> findBookByPriceOldIs(Integer price);

    @Query("from Book where isBestseller=1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();
}
