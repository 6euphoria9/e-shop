package com.euphoria.ecommerce.controller;

import com.euphoria.ecommerce.model.Product;
import com.euphoria.ecommerce.service.OrderService;
import com.euphoria.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    // Панель администратора
    @GetMapping
    public String adminPanel() {
        return "admin";
    }

    // Управление товарами
    @GetMapping("/products")
    public String manageProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "manage-products";
    }

    // Форма для добавления/редактирования товара
    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // Сохранение нового товара
    @PostMapping("/products")
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    // Управление заказами
    @GetMapping("/orders")
    public String manageOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "manage-orders";
    }
}