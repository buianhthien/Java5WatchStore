package edu.poly.pd11347_asm.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.pd11347_asm.models.Category;
import edu.poly.pd11347_asm.models.Product;
import edu.poly.pd11347_asm.repository.CategoryRepository;
import edu.poly.pd11347_asm.repository.ProductRepository;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/product")
public class ProductAController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String index(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("view", "admin/productCRUD");
        return "admin/layout";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);

        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("view", "admin/productCRUD");
        return "admin/layout";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product, @RequestParam("photoFile") MultipartFile photoFile,
            Model model) {
        if (!photoFile.isEmpty()) {
            String uploadDir = "D:\\SOF3022_Java5\\images\\";
            String fileName = System.currentTimeMillis() + "-" + photoFile.getOriginalFilename();
            File savedFile = new File(uploadDir + fileName);
            try {
                photoFile.transferTo(savedFile);
                product.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/admin/product/index?error=upload_failed";
            }
        }
        productRepository.save(product);
        // Trả về danh sách sản phẩm sau khi thêm
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "redirect:/admin/product/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product, @RequestParam("photoFile") MultipartFile photoFile,
            Model model) {
        Product existingProduct = productRepository.findById(Long.valueOf(product.getProductId())).orElse(null);
        if (existingProduct == null) {
            return "redirect:/admin/product/index?error=not_found";
        }

        if (!photoFile.isEmpty()) { // Nếu có ảnh mới được tải lên
            String uploadDir = "D:\\SOF3022_Java5\\images\\";
            String fileName = System.currentTimeMillis() + "-" + photoFile.getOriginalFilename();
            File savedFile = new File(uploadDir + fileName);
            try {
                photoFile.transferTo(savedFile);
                product.setImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            product.setImage(existingProduct.getImage()); // Giữ ảnh cũ nếu không upload mới
        }

        productRepository.save(product);
        // Trả về danh sách sản phẩm sau khi cập nhật
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        return "redirect:/admin/product/index";
    }
}
