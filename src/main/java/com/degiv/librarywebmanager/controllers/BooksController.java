package com.degiv.librarywebmanager.controllers;

import com.degiv.librarywebmanager.dao.BookDAO;
import com.degiv.librarywebmanager.dao.VisitorDAO;
import com.degiv.librarywebmanager.models.Book;
import com.degiv.librarywebmanager.models.Visitor;
import com.degiv.librarywebmanager.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final VisitorDAO visitorDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, VisitorDAO visitorDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.visitorDAO = visitorDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/create")
    public String createPage(@ModelAttribute("book") Book book) {
        return "books/create";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/create";
        }
        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("owner", visitorDAO.getVisitor(bookDAO.getVisitorIdByBookId(id)));
        model.addAttribute("newOwner", new Visitor());
        model.addAttribute("visitors", visitorDAO.index());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBook(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.edit(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int id) {
        bookDAO.freeBook(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/setOwner")
    public String setOwner(@ModelAttribute("newOwner") Visitor newOwner, @PathVariable("id") int id) {
        bookDAO.setOwner(id, newOwner.getVisitor_id());
        return "redirect:/books/" + id;
    }
}
