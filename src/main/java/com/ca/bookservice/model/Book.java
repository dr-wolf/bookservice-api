package com.ca.bookservice.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column
    @NotEmpty
    @Length(max = 256)
    private String title;

    @Column
    @NotEmpty
    @Length(max = 256)
    private String author;

    @Column
    @NotNull
    @Min(value = 0)
    private Integer year;

    public Book(@NotEmpty @Length(max = 256) String title, @NotEmpty @Length(max = 256) String author, @NotEmpty @Min(value = 0) Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
