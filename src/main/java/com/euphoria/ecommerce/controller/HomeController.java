package com.euphoria.ecommerce.controller;

import com.euphoria.ecommerce.model.Product;
import com.euphoria.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {

        List<Product> popularProducts = productService.getPopularProducts();
        model.addAttribute("popularProducts", popularProducts);

        return "home";
    }

}
