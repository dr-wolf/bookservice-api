package com.ca.bookservice.controller;

import com.ca.bookservice.model.Book;
import com.ca.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method=RequestMethod.GET, path="/book")
    public List<Book> all(@RequestParam(required = false, name = "title") String title,
                          @RequestParam(required = false, name = "author") String author,
                          @RequestParam(required = false, name = "year") Integer year) {
        return bookService.find(title, author, year);
    }

    @RequestMapping(method=RequestMethod.GET, path="/book/{id}")
    public Book one(@PathVariable("id") Long id) {
        return bookService.one(id);
    }

    @RequestMapping(method=RequestMethod.POST, path="/book")
    public Book create(@Valid @RequestBody Book book) {
        return bookService.create(book);
    }

    @RequestMapping(method=RequestMethod.POST, path="/book/{id}")
    public Book update(@Valid @RequestBody Book book, @PathVariable("id") Long id) {
        book.setId(id);
        return bookService.update(book);
    }

    @RequestMapping(method=RequestMethod.DELETE, path="/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

}
