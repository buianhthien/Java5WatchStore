package edu.poly.pd11347_asm.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ChangPassword {
@GetMapping("/changPassword")
public String chang(Model model){
    model.addAttribute("view", "account-page/changPassword");
    return "layouts/layout";
}

}
