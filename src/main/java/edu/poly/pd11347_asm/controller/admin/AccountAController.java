package edu.poly.pd11347_asm.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.pd11347_asm.models.Account;
import edu.poly.pd11347_asm.repository.AccountRepository;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/account")
public class AccountAController {
    @Autowired
    AccountRepository accountDAO;

    @GetMapping("/index")
    public String index(Model model) {
        edu.poly.pd11347_asm.models.Account account = new Account();
        model.addAttribute("account", account);
        List<Account> accounts = accountDAO.findAll();
        model.addAttribute("accounts", accounts);
        model.addAttribute("view", "admin/userCRUD");
        return "admin/layout";
    }

    @GetMapping("/edit/{username}")
    public String edit(Model model, @PathVariable("username") String username) {
        Account account = accountDAO.findById(username).get();
        model.addAttribute("account", account);
        List<Account> accounts = accountDAO.findAll();
        model.addAttribute("accounts", accounts);
        model.addAttribute("view", "admin/userCRUD");
        return "admin/layout";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Account account,
            @RequestParam(value = "photoFile", required = false) MultipartFile photoFile, Model model) {
        if (photoFile != null && !photoFile.isEmpty()) {
            String uploadDir = "D:\\SOF3022_Java5\\images\\";
            String fileName = System.currentTimeMillis() + "-" + photoFile.getOriginalFilename();
            File savedFile = new File(uploadDir + fileName);
            try {
                photoFile.transferTo(savedFile);
                account.setPhoto(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/admin/account/index?error=upload_failed";
            }
        }
        accountDAO.save(account);
        return "redirect:/admin/account/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Account account,
            @RequestParam(value = "photoFile", required = false) MultipartFile photoFile,
            Model model) {
        Account existingAccount = accountDAO.findById(account.getUsername()).orElse(null);
        if (existingAccount == null) {
            return "redirect:/admin/account/index?error=not_found";
        }

        // Nếu người dùng không nhập mật khẩu mới, giữ nguyên mật khẩu cũ
        if (account.getPassword() == null || account.getPassword().isEmpty()) {
            account.setPassword(existingAccount.getPassword());
        }

        if (!photoFile.isEmpty()) { // Nếu có ảnh mới được tải lên
            String uploadDir = "D:\\SOF3022_Java5\\images\\";
            String fileName = System.currentTimeMillis() + "-" + photoFile.getOriginalFilename();
            File savedFile = new File(uploadDir + fileName);
            try {
                photoFile.transferTo(savedFile);
                account.setPhoto(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            account.setPhoto(existingAccount.getPhoto()); // Giữ ảnh cũ nếu không upload mới
        }

        accountDAO.save(account);
        // Trả về danh sách sản phẩm sau khi cập nhật
        List<Account> accounts = accountDAO.findAll();
        model.addAttribute("accounts", accounts);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/delete/{username}")
    public String delete(@PathVariable("username") String username) {
        if (accountDAO.existsById(username)) {
            accountDAO.deleteById(username);
        }
        return "redirect:/admin/account/index";
    }

}
