package com.euphoria.e_shop.service;

import com.euphoria.e_shop.model.Cart;
import com.euphoria.e_shop.model.CartItem;
import com.euphoria.e_shop.model.Product;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final Cart cart = new Cart();

    public Cart getCart() {
        return cart;
    }

    public void addProduct(Product product) {
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setQuantity(1);
        cart.getItems().add(newItem);
    }

    public void removeProduct(Long productId) {
        cart.removeProduct(productId);
    }

    public double getTotalPrice() {
        return cart.getTotalPrice();
    }

    public void updateQuantity(Long productId, int quantity) {
        cart.updateProductQuantity(productId, quantity);
    }

    public void incrementProductQuantity(Long productId) {
        cart.incrementProductQuantity(productId);
    }

    public void decrementProductQuantity(Long productId) {
        cart.decrementProductQuantity(productId);
    }
}
