package edu.poly.pd11347_asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Signup {
@GetMapping("/signup")
public String signup(Model model){
    model.addAttribute("view", "account-page/signup");
    return "layouts/layout";
}
}
