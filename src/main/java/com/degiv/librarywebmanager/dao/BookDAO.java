package com.degiv.librarywebmanager.dao;

import com.degiv.librarywebmanager.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM BOOK", new BeanPropertyRowMapper<>(Book.class));
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }
    public void edit(Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE book_id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getId());
    }
    public Book getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream()
                .findAny().orElse(null);
    }

    public Book getBook(String author, String title) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE author=? AND title=?",
                        new Object[]{author, title}, new BeanPropertyRowMapper<>(Book.class)).stream()
                .findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}
