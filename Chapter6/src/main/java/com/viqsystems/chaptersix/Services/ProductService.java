package com.viqsystems.chaptersix.Services;

import com.viqsystems.chaptersix.Entities.Product;
import com.viqsystems.chaptersix.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
