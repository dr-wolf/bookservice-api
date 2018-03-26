package com.ca.bookservice.service;

import com.ca.bookservice.exception.NotFound;
import com.ca.bookservice.model.Book;
import com.ca.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> find(String title, String author, Integer year) {
        if (title == null && author == null && year == null) {
            return bookRepository.findAll();
        }
        return bookRepository.search(title, author, year);
    }

    @Override
    public Book getById(Long id) throws NotFound {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new NotFound();
        }
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) throws NotFound {
        Book oldBook = getById(book.getId());
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setYear(book.getYear());
        return bookRepository.save(oldBook);
    }

    @Override
    public void delete(Long id) throws NotFound {
        bookRepository.delete(getById(id));
    }

}
