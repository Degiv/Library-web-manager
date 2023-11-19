package com.degiv.librarywebmanager.dao;

import com.degiv.librarywebmanager.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }
    public void edit(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE book_id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                id);
    }
    public Book getBook(int id) {
        Book book = jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                        new Object[]{id}, new BookMapper()).stream()
                .findAny().orElse(null);

        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new Object[]{id}, new BookMapper()).stream()
                .findAny().orElse(null);
    }
    public Book getBook(String author, String title) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE author=? AND title=?",
                        new Object[]{author, title}, new BookMapper()).stream()
                .findAny().orElse(null);
    }

    public List<Book> getBooksByVisitorId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE visitor_id=?", new Object[]{id}, new BookMapper());
    }

    public void freeBook(int id) {
        jdbcTemplate.update("UPDATE Book SET visitor_id=? WHERE book_id=?", null, id);
    }

    public void setOwner(int bookId, int visitorId) {
        jdbcTemplate.update("UPDATE Book SET visitor_id=? WHERE book_id=?", visitorId, bookId);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public int getVisitorIdByBookId(int id) {
        return jdbcTemplate.query("SELECT visitor_id FROM Book WHERE book_id=?", new Object[]{id}, new RowMapper<Integer>(){
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("visitor_id");
            }
        }).stream().findAny().orElse(0);
    }
}
