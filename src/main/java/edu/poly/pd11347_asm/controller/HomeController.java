package edu.poly.pd11347_asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import edu.poly.pd11347_asm.repository.CategoryRepository;
import edu.poly.pd11347_asm.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;


@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findNewProducts(PageRequest.of(0, 4)));
        model.addAttribute("productsTop5", productRepository.findTop4ByOrderByPriceAsc());
        model.addAttribute("view", "user-page/home");
        return "layouts/layout";
    }

}
