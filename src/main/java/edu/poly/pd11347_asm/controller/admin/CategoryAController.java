package edu.poly.pd11347_asm.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.pd11347_asm.models.Category;
import edu.poly.pd11347_asm.repository.CategoryRepository;
import edu.poly.pd11347_asm.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryAController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryDAO;

    @GetMapping("/index")
    public String index(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("view", "admin/product_categoryCRUD");
        return "admin/layout";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Long categoryId = Long.parseLong(id);
        Category category = categoryRepository.findById(categoryId).get();
        model.addAttribute("category", category);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("view", "admin/product_categoryCRUD");
        return "admin/layout";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/category/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Category category) {
        if (categoryRepository.existsById(category.getCategoryId())) {
            categoryRepository.save(category);
        }
        return "redirect:/admin/category/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        try {
            Long categoryId = Long.parseLong(id);
            if (categoryRepository.existsById(categoryId)) {
                try {
                    categoryRepository.deleteById(categoryId);
                    redirectAttributes.addFlashAttribute("success", "Xóa danh mục thành công!");
                } catch (DataIntegrityViolationException e) {
                    redirectAttributes.addFlashAttribute("error", "Không thể xóa danh mục vì đang được sử dụng!");
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy danh mục!");
            }
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "ID không hợp lệ!");
        }
        return "redirect:/admin/category/index";
    }
}
