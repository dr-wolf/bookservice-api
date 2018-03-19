package com.ca.bookservice.repository;

import com.ca.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select id, title, author, year from Book where" +
            "(:title = null or title like %:title%) and" +
            "(:author = null or author = :author) and" +
            "(:year = null or year = :year)")
    public List<Book> search(@Param(value = "title") String title,
                             @Param(value = "author") String author,
                             @Param(value = "year") Integer year);

}
