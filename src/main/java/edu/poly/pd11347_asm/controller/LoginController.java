package edu.poly.pd11347_asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.pd11347_asm.repository.AccountRepository;
import edu.poly.pd11347_asm.service.AuthService;
import edu.poly.pd11347_asm.service.SessionService;
import edu.poly.pd11347_asm.models.Account;

@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    AccountRepository accountDAO;

    @Autowired
    AuthService authService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("view", "account-page/login");
        return "layouts/layout";
    }

    @PostMapping("/login")
    public String login(RedirectAttributes redirectAttributes,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "remember", required = false) String remember) {
        
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            return "redirect:/auth/login";
        }

        Account account = accountDAO.findById(username).orElse(null);
        
        if (account == null) {
            redirectAttributes.addFlashAttribute("error", "Tài khoản không tồn tại!");
            return "redirect:/auth/login";
        }

        if (!Boolean.TRUE.equals(account.isActivated())) {
            redirectAttributes.addFlashAttribute("error", "Tài khoản chưa được kích hoạt!");
            return "redirect:/auth/login";
        }

        if (!account.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("error", "Sai mật khẩu!");
            return "redirect:/auth/login";
        }

        // Nếu người dùng chọn "Remember me" thì mới lưu vào session
        if (remember != null) {
            sessionService.set("user", account);
        }

        // Đăng nhập thành công
        redirectAttributes.addFlashAttribute("success", "Đăng nhập thành công!");
        return "redirect:/home";
    }
    
     // Xử lý đăng xuất
     @GetMapping("/logout")
     public String logout(RedirectAttributes redirectAttributes) {
         sessionService.remove("user"); 
         redirectAttributes.addFlashAttribute("success", "Đăng xuất thành công!");
         return "redirect:/home";
    }
}
