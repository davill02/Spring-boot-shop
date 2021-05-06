package com.spring.boot.phoneshop.controllers;

import com.spring.boot.phoneshop.entities.Product;
import com.spring.boot.phoneshop.services.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Optional;

import static com.spring.boot.phoneshop.controllers.ControllersConstants.CATEGORIES;
import static com.spring.boot.phoneshop.controllers.ControllersConstants.COUNT_ATTR;
import static com.spring.boot.phoneshop.controllers.ControllersConstants.PHONES_ATTR;

@Controller
public class ProductListPageController {
    @Resource
    PhoneService phoneService;

    @GetMapping("/phone")
    public String getAllPhones(Model model,
                               @RequestParam(value = "category", required = false) String category,
                               @RequestParam(value = "field", required = false) String field,
                               @RequestParam(value = "order", required = false) String order,
                               @RequestParam(value = "page", required = false) String page) {
        model.addAttribute(PHONES_ATTR, phoneService.getByParameters(category, field, order, page));
        model.addAttribute(COUNT_ATTR, phoneService.getCount(category, field, order, page));
        model.addAttribute(CATEGORIES, phoneService.getAllCategories());
        return "phones-page";
    }

}
