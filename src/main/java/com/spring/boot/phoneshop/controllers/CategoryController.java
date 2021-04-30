package com.spring.boot.phoneshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping
    public String getStartPage(Model model) {
        model.addAttribute(ControllersConstants.CATEGORIES, Category.values());
        return "start-page";
    }
}
