package com.ca.bookservice.service;

import com.ca.bookservice.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> find(String title, String author, Integer year);

    public Book getById(Long id);

    public Book create(Book book);

    public Book update(Long id, Book book);

    public void delete(Long id);

}
