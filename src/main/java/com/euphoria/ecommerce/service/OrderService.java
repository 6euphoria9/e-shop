package com.euphoria.ecommerce.service;

import com.euphoria.ecommerce.model.Cart;
import com.euphoria.ecommerce.model.Order;
import com.euphoria.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    public Order createOrder(String customerName, String customerEmail, String customerAddress) {
        Cart cart = cartService.getCart();
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setCustomerEmail(customerEmail);
        order.setCustomerAddress(customerAddress);
        order.setItems(new ArrayList<>(cart.getItems()));
        order.setTotalPrice(cart.getTotalPrice());
        cart.getItems().clear(); // Очищаем корзину после заказа
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
