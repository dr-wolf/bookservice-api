package com.ca.bookservice.service;

import com.ca.bookservice.exception.NotFound;
import com.ca.bookservice.model.Book;
import com.ca.bookservice.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_all() throws Exception {
        try {
            Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(makeBook()));
            List<Book> res = bookService.find(null, null, null);
            Assert.assertFalse("Result shouldn't be empty", res.isEmpty());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test_all_search() throws Exception {
        try {
            String title = "title";
            Mockito.when(bookRepository.search(title, null, null)).thenReturn(Arrays.asList(makeBook()));
            List<Book> res = bookService.find(title, null, null);
            Assert.assertFalse("Result shouldn't be empty", res.isEmpty());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test_one() throws Exception {
        try {
            Long id = 1L;
            Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(makeBook()));
            Book res = bookService.getById(id);
            Assert.assertNotNull("Result shouldn't be null", res);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(expected = NotFound.class)
    public void test_one_NotFound() throws Exception {
        Long id = 1L;
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());
        bookService.getById(id);
    }

    @Test
    public void test_create() throws Exception {
        try {
            Book book = makeBook();
            Mockito.doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                    return invocationOnMock.getArgument(0);
                }
            }).when(bookRepository).save(book);
            Book res = bookService.create(book);
            Assert.assertEquals("Result shouldn't be equal to giver", book, res);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test_update() throws Exception {
        try {
            Long id = 1L;
            Book book = makeBook(id);
            Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
            Mockito.doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                    return invocationOnMock.getArgument(0);
                }
            }).when(bookRepository).save(book);
            Book res = bookService.update(makeBook(id));
            Assert.assertNotNull("Result shouldn't be null", res);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(expected = NotFound.class)
    public void test_update_NotFound() throws Exception {
        Long id = 1L;
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());
        bookService.update(makeBook(id));
    }

    @Test
    public void test_delete() throws Exception {
        try {
            Long id = 1L;
            Book book = makeBook(id);
            Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
            Mockito.doAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                    return invocationOnMock.getArgument(0);
                }
            }).when(bookRepository).delete(book);
            bookService.delete(id);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(expected = NotFound.class)
    public void test_delete_NotFound() throws Exception {
        Long id = 1L;
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());
        bookService.delete(id);
    }

    private Book makeBook() {
        return new Book("title", "author", 2018);
    }

    private Book makeBook(Long id) {
        return new Book("title", "author", 2018) {{  setId(id); }};
    }
}
