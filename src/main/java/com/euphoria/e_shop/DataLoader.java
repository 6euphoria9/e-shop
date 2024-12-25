package com.euphoria.e_shop;

import com.euphoria.e_shop.model.Product;
import com.euphoria.e_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        List<String> testImageURLs = new ArrayList<>();
        testImageURLs.add("https://via.placeholder.com/150");

        productRepository.save(new Product(null, "Футболка", "Классическая белая футболка", 19.99, testImageURLs, true));
        productRepository.save(new Product(null, "Джинсы", "Синие джинсы slim-fit", 49.99, testImageURLs,true));
        productRepository.save(new Product(null, "Кроссовки", "Удобные кроссовки для бега", 69.99, testImageURLs, true));
    }
}
