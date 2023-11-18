package com.degiv.librarywebmanager.util;

import com.degiv.librarywebmanager.dao.VisitorDAO;
import com.degiv.librarywebmanager.models.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VisitorValidator implements Validator {
    private VisitorDAO visitorDAO;

    @Autowired
    public VisitorValidator(VisitorDAO visitorDAO) {
        this.visitorDAO = visitorDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Visitor.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Visitor visitor = (Visitor) o;
        if (visitorDAO.getVisitor(visitor.getFullName()) != null) {
            errors.rejectValue("fullName", "", "This visitor already exists");
        }
    }
}
