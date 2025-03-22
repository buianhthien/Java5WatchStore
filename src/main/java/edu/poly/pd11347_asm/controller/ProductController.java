package edu.poly.pd11347_asm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.pd11347_asm.models.Category;
import edu.poly.pd11347_asm.models.Product;
import edu.poly.pd11347_asm.repository.CategoryRepository;
import edu.poly.pd11347_asm.repository.ProductRepository;

@Controller
public class ProductController {
    @Autowired
    CategoryRepository categoryDAO;

    @Autowired
    ProductRepository productDAO;

    @GetMapping("/product")
    public String listProducts(Model model,
            @RequestParam(name = "category", required = false) String categoryId,
            @RequestParam(defaultValue = "0") int page) {

        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);

        Pageable pageable = PageRequest.of(page, 12);
        Page<Product> productPage;

        if (categoryId != null && !categoryId.isEmpty()) {
            productPage = productDAO.findNewProducts(pageable);
        } else {
            productPage = productDAO.findAll(pageable);
        }

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("view", "user-page/product");
        return "layouts/layout";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products;
        if (keyword.isEmpty()) {
            products = productDAO.findAll();
        } else {
            products = productDAO.findByProductNameContainingIgnoreCase(keyword);
        }
        model.addAttribute("products", products);
        model.addAttribute("view", "admin/productCRUD");
        return "admin/layout";
    }
}
