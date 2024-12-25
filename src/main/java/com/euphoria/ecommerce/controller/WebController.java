package com.euphoria.ecommerce.controller;

import com.euphoria.ecommerce.model.Product;
import com.euphoria.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProductsPage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("products", productService.searchProducts(keyword));
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }
        return "products";
    }

    @GetMapping("/products/filter")
    public String filterProducts(@RequestParam double minPrice, @RequestParam double maxPrice, Model model) {
        model.addAttribute("products", productService.filterByPrice(minPrice, maxPrice));
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProductPage(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product";
    }

}
