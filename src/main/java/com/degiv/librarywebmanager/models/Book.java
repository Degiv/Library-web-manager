package com.degiv.librarywebmanager.models;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book {
    private int book_id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    @NumberFormat
    private int year;

    public Book() { }
    public Book(int id, String title, String author, int year) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
