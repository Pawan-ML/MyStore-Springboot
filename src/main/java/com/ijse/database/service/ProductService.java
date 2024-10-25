package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.dto.ProductDTO;
import com.ijse.database.entity.Product;

@Service
public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(ProductDTO productDTO);
    Product getProductById(Long id);
    Product updateProduct(Long id, Product product);
    boolean  deleteProduct(Long id);
    List<Product> getProductsByCategory(Long id);
}
