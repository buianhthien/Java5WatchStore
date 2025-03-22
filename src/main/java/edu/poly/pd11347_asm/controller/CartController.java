package edu.poly.pd11347_asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CartController {
    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("view", "user-page/cart");
        return "layouts/layout";
    }
    
}
