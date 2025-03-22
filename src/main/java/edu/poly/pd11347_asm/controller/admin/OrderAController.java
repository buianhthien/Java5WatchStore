package edu.poly.pd11347_asm.controller.admin;

import edu.poly.pd11347_asm.models.Order;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.poly.pd11347_asm.repository.OrderRepository;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/order")
public class OrderAController {
    @Autowired
    OrderRepository orderDAO;

    @GetMapping("/index")
    public String index( Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        List<Order> orders = orderDAO.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("view", "admin/order");
        return "admin/layout";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Order order = orderDAO.findById(id).get();
        model.addAttribute("order", order);
        List<Order> orders = orderDAO.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("view", "admin/order");
        return "admin/layout";
    }
    
    @PostMapping("/create")
    public String create(@ModelAttribute Order order) {
        orderDAO.save(order);
        return "redirect:/admin/order/index";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute Order order) {
       if(orderDAO.existsById(order.getOrderId())){
        orderDAO.save(order);
       }
        return "redirect:/admin/order/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (orderDAO.existsById(id)) {
            orderDAO.deleteById(id);
        }
        return "redirect:/admin/order/index";
    }
}
