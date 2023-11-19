package com.degiv.librarywebmanager.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Visitor {
    private int visitor_id;

    @NotEmpty
    private String fullName;

    @NumberFormat
    private int yearOfBirth;

    public Visitor() { }
    public Visitor(int visitor_id, String fullName, int yearOfBirth) {
        this.visitor_id = visitor_id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(int visitor_id) {
        this.visitor_id = visitor_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
