package com.degiv.librarywebmanager.controllers;

import com.degiv.librarywebmanager.dao.BookDAO;
import com.degiv.librarywebmanager.dao.VisitorDAO;
import com.degiv.librarywebmanager.models.Visitor;
import com.degiv.librarywebmanager.util.VisitorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/visitors")
public class VisitorsController {
    private final VisitorDAO visitorDAO;
    private final BookDAO bookDAO;
    private final VisitorValidator visitorValidator;

    @Autowired
    public VisitorsController(VisitorDAO visitorDAO, BookDAO bookDAO, VisitorValidator visitorValidator) {
        this.visitorDAO = visitorDAO;
        this.bookDAO = bookDAO;
        this.visitorValidator = visitorValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("visitors", visitorDAO.index());
        return "visitors/index";
    }

    @GetMapping("/create")
    public String createPage(@ModelAttribute("visitor") Visitor visitor) {
        return "visitors/create";
    }

    @PostMapping()
    public String create(@ModelAttribute("visitor") @Valid Visitor visitor, BindingResult bindingResult) {
        visitorValidator.validate(visitor, bindingResult);
        if (bindingResult.hasErrors()) {
            return "visitors/create";
        }
        visitorDAO.create(visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("visitor", visitorDAO.getVisitor(id));
        model.addAttribute("takenBooks", bookDAO.getBooksByVisitorId(id));
        return "visitors/show";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("visitor", visitorDAO.getVisitor(id));
        System.out.println(visitorDAO.getVisitor(id).getFullName());
        return "visitors/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute @Valid Visitor visitor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        visitorDAO.edit(visitor, id);
        return "redirect:/visitors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        visitorDAO.delete(id);
        return "redirect:/visitors";
    }
}
