package com.spring.boot.phoneshop.controllers;

import com.spring.boot.phoneshop.entities.Phone;
import com.spring.boot.phoneshop.repositories.PhoneRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Optional;

import static com.spring.boot.phoneshop.controllers.ControllersConstants.COUNT_ATTR;
import static com.spring.boot.phoneshop.controllers.ControllersConstants.PHONES_ATTR;

@Controller
public class PhoneController {
    @Resource
    PhoneRepository phoneRepository;

    @GetMapping("/Phone")
    public String getAllPhones(Model model) {
        model.addAttribute(PHONES_ATTR, phoneRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "price"))));
        model.addAttribute(COUNT_ATTR, phoneRepository.count());
        return "phones-page";
    }

    @GetMapping("/Phone/{id}")
    public String getProductDetailPage(@PathVariable Long id, Model model) {
        Optional<Phone> phone = phoneRepository.findById(id);
        if(phone.isPresent()){
            model.addAttribute("phoneDetail",phone.get() );
            return "phone-details";
        }else{
            return "not-found";
        }

    }
}
