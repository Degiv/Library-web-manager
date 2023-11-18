package com.degiv.librarywebmanager.controllers;

import com.degiv.librarywebmanager.dao.VisitorDAO;
import com.degiv.librarywebmanager.models.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/visitors")
public class VisitorController {
    private VisitorDAO visitorDAO;

    @Autowired
    public VisitorController(VisitorDAO visitorDAO) {
        this.visitorDAO = visitorDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("visitors", visitorDAO.index());
        return "visitor/index";
    }

    @GetMapping("/new")
    public String createPage(@ModelAttribute("visitor") Visitor visitor) {
        return "visitor/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("visitor") Visitor visitor) {
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
    public String edit(@PathVariable("id") int id, @ModelAttribute Visitor visitor) {
        visitorDAO.edit(visitor);
        return "redirect:/visitors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        visitorDAO.delete(id);
        return "redirect:/visitors";
    }
}
