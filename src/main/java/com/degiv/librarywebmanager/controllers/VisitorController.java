package com.degiv.librarywebmanager.controllers;

import com.degiv.librarywebmanager.dao.VisitorDAO;
import com.degiv.librarywebmanager.models.Visitor;
import com.degiv.librarywebmanager.util.VisitorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/visitors")
public class VisitorController {
    private VisitorDAO visitorDAO;
    private VisitorValidator visitorValidator;

    @Autowired
    public VisitorController(VisitorDAO visitorDAO, VisitorValidator visitorValidator) {
        this.visitorDAO = visitorDAO;
        this.visitorValidator = visitorValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("visitors", visitorDAO.index());
        return "visitor/index";
    }

    @GetMapping("/create")
    public String createPage(@ModelAttribute("visitor") Visitor visitor) {
        return "visitor/create";
    }

    @PostMapping()
    public String create(@ModelAttribute("visitor") Visitor visitor, BindingResult bindingResult) {
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
        return "visitors/show";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("visitor", visitorDAO.getVisitor(id));
        return "visitors/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute Visitor visitor, BindingResult bindingResult) {
        visitorValidator.validate(visitor, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        visitorDAO.edit(visitor);
        return "redirect:/visitors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        visitorDAO.delete(id);
        return "redirect:/visitors";
    }
}
