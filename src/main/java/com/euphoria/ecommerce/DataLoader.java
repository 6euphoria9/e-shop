package com.euphoria.ecommerce;

import com.euphoria.ecommerce.model.Product;
import com.euphoria.ecommerce.repository.ProductRepository;
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

        productRepository.save(new Product(null, "Футболка #1", "Классическая белая футболка", 19.99, testImageURLs, true));
        productRepository.save(new Product(null, "Джинсы #1", "Синие джинсы slim-fit", 49.99, testImageURLs,true));
        productRepository.save(new Product(null, "Кроссовки #1", "Удобные кроссовки для бега", 69.99, testImageURLs, true));

        productRepository.save(new Product(null, "Футболка #2", "Классическая белая футболка", 33.90, testImageURLs, true));
        productRepository.save(new Product(null, "Джинсы #2", "Синие джинсы slim-fit", 85.90, testImageURLs,true));
        productRepository.save(new Product(null, "Кроссовки #2", "Удобные кроссовки для бега", 79.90, testImageURLs, false));
    }
}
