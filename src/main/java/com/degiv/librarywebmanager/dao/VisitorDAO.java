package com.degiv.librarywebmanager.dao;

import com.degiv.librarywebmanager.models.Book;
import com.degiv.librarywebmanager.models.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisitorDAO {
    private final JdbcTemplate jdbcTemplate;
    private int update;

    @Autowired
    public VisitorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Visitor> index() {
        return jdbcTemplate.query("SELECT * FROM Visitor", new BeanPropertyRowMapper<>(Visitor.class));
    }

    public void create(Visitor visitor) {
        jdbcTemplate.update("INSERT INTO (full_name, year_of_birth) VALUES(?)",
                visitor.getFullName(),
                visitor.getYearOfBirth());
    }
    public void edit(Visitor visitor) {
        jdbcTemplate.update("UPDATE Visitor SET full_name=?, year_of_birth=? WHERE visitor_id=?",
                visitor.getFullName(),
                visitor.getYearOfBirth(),
                visitor.getId());
    }
    public Visitor getVisitor(int id) {
        return jdbcTemplate.query("SELECT * FROM Visitor WHERE visitor_id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Visitor.class)).stream()
                .findAny().orElse(null);
    }

    public Book getVisitor(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Visitor WHERE fulll_mame=?",
                        new Object[]{fullName}, new BeanPropertyRowMapper<>(Book.class)).stream()
                .findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Visitor WHERE visitor_id=?", id);
    }
}
