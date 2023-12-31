package com.degiv.librarywebmanager.dao;

import com.degiv.librarywebmanager.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBook_id(resultSet.getInt("book_id"));
        book.setTitle(resultSet.getString("title"));
        book.setYear(resultSet.getInt("year"));
        book.setAuthor(resultSet.getString("author"));
        return book;
    }
}
