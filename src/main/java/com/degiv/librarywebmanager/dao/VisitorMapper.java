package com.degiv.librarywebmanager.dao;

import com.degiv.librarywebmanager.models.Visitor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitorMapper implements RowMapper<Visitor> {
    @Override
    public Visitor mapRow(ResultSet resultSet, int i) throws SQLException {
        Visitor visitor = new Visitor();
        visitor.setVisitor_id(resultSet.getInt("visitor_id"));
        visitor.setYearOfBirth(resultSet.getInt("year_of_birth"));
        visitor.setFullName(resultSet.getString("full_name"));
        return visitor;
    }
}
