package com.spring.boot.phoneshop.controllers;

import com.spring.boot.phoneshop.entities.Product;
import com.spring.boot.phoneshop.services.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Optional;

import static com.spring.boot.phoneshop.controllers.ControllersConstants.PHONE_DETAIL_ATTR;

@Controller
public class ProductDetailsPageController {
    @Resource
    PhoneService service;

    @GetMapping("/phones/{id}")
    public String getProductDetailPage(@PathVariable Long id, Model model) {
        Optional<Product> phone = service.getById(id);
        if (phone.isPresent()) {
            model.addAttribute(PHONE_DETAIL_ATTR, phone.get());
            return "phone-details";
        } else {
            return "not-found";
        }
    }
}
