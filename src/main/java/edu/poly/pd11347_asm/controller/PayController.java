package edu.poly.pd11347_asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PayController {
   @GetMapping("/payment")
   public String pay(Model model) {
    model.addAttribute("view", "user-page/payment");
    return "layouts/layout";
    }
}
