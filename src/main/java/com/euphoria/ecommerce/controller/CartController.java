package com.euphoria.ecommerce.controller;

import com.euphoria.ecommerce.model.CartItem;
import com.euphoria.ecommerce.model.Product;
import com.euphoria.ecommerce.service.CartService;
import com.euphoria.ecommerce.service.OrderService;
import com.euphoria.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart";
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public List<CartItem> addToCart(@RequestParam Long productId) {
        Product product = productService.getAllProducts()
                .stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
        if (product != null) {
            cartService.addProduct(product);
        }
        return cartService.getCart().getItems();
    }

    @PostMapping("/cart/remove")
    @ResponseBody
    public List<CartItem> removeFromCart(@RequestParam Long productId) {
        cartService.removeProduct(productId);
        return cartService.getCart().getItems();
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String customerName,
                           @RequestParam String customerEmail,
                           @RequestParam String customerAddress) {
        orderService.createOrder(customerName, customerEmail, customerAddress);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String showConfirmationPage() {
        return "confirmation";
    }


    @PostMapping("/cart/increment")
    @ResponseBody
    public List<CartItem> incrementCartQuantity(@RequestParam Long productId) {
        cartService.incrementProductQuantity(productId);
        return cartService.getCart().getItems();
    }

    @PostMapping("/cart/decrement")
    @ResponseBody
    public List<CartItem> decrementCartQuantity(@RequestParam Long productId) {
        cartService.decrementProductQuantity(productId);
        return cartService.getCart().getItems();
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public List<CartItem> updateCartQuantity(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);
        return cartService.getCart().getItems();
    }

    @GetMapping("/cart/mini")
    public String getMiniCart(Model model) {
        List<CartItem> items = cartService.getCart().getItems();
        model.addAttribute("cartItems", items != null ? items : Collections.emptyList());
        return "fragments/mini-cart :: miniCart";
    }

    @GetMapping("/cart/state")
    @ResponseBody
    public List<CartItem> getCartState() {
        return cartService.getCart().getItems();
    }
}
