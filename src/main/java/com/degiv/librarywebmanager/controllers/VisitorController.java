package com.degiv.librarywebmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitors")
public class VisitorController {
    @GetMapping
    public void index() {

    }
}
