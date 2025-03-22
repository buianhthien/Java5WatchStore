package edu.poly.pd11347_asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.poly.pd11347_asm.repository.CategoryRepository;
import edu.poly.pd11347_asm.models.Category;
import edu.poly.pd11347_asm.models.Product;
import edu.poly.pd11347_asm.repository.ProductRepository;

@Controller
public class DetailController {
    @Autowired
    CategoryRepository categoryDAO;

    @Autowired
    ProductRepository productDAO;

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("id") Integer id) {
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);

        Product product = productDAO.findById(id.longValue()).orElse(null);
        if (product == null) {
            return "redirect:/error"; 
        }

        model.addAttribute("product", product);
        model.addAttribute("view", "user-page/detail");
        return "layouts/layout";
    }

}
