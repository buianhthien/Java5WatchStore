package edu.poly.pd11347_asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.ui.Model;
import edu.poly.pd11347_asm.models.Category;
import edu.poly.pd11347_asm.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    // @Autowired
    // private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);

        return "admin/categoryCRUD";
    }

    @GetMapping("/edit/{categoryId}")
    public String edit(Model model, @PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);
        ;
        return "admin/categoryCRUD";

    }

    @PostMapping("/saveOrUpdate")
    public String saveUpdate(Model model, Category category) {
        categoryService.add(category);
        return "redirect:/Category/list";
    }

    @GetMapping("/delete/{categoryId}")
    public String delete(RedirectAttributesModelMap model, @PathVariable("categoryID") Long categoryId) {

        categoryService.delete(categoryId);

        model.addAttribute("message", "Category is deleted");
        return "redirect:/Category/list";
    }



    @GetMapping("/list")
    public String list(Model model) {
        var categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "Categories/list";
    }

    @GetMapping("/page")
    public String page(Model model,
            @PageableDefault(size = 2, direction = Direction.ASC, page = 0, sort = "categoryName") Pageable pageable) {
        var page = categoryService.findAll(pageable);
        model.addAttribute("categoryPage", page);
        return "Categories/page";
    }

    @GetMapping("/search")
    public String search(Model model,
            @RequestParam(name = "keyword", required = false) String keyword,
            @PageableDefault(size = 2, direction = Direction.ASC, page = 0, sort = "categoryName") Pageable pageable) {
        var page = categoryService.search("%" + keyword + "%", pageable);
        model.addAttribute("categoryPage", page);
        return "Categories/page";
    }

}
