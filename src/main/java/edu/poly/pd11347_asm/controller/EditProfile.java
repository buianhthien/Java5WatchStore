package edu.poly.pd11347_asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class EditProfile {
@GetMapping("/editProfile")
public String forgot(Model model){
    model.addAttribute("view", "account-page/profile");
    return "layouts/layout";
}
}
