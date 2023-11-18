package com.degiv.librarywebmanager.util;

import com.degiv.librarywebmanager.dao.BookDAO;
import com.degiv.librarywebmanager.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Book.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if (bookDAO.getBook(book.getAuthor(), book.getTitle()) != null) {
            errors.reject("This book already exists");
        }
    }
}
