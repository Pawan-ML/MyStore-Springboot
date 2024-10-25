package com.ijse.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.dto.ProductDTO;
import com.ijse.database.entity.Category;
import com.ijse.database.entity.Product;
import com.ijse.database.repository.CategoryRepository;
import com.ijse.database.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired 
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);

        if(category != null) {
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setQty(productDTO.getQty());
            product.setCategory(category);
            
            return productRepository.save(product);
        } else {
            return null;
        }

        
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if(existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQty(product.getQty());
            
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if(category != null) {
            return productRepository.findProductsByCategory(category);
        } else {
            return null;
        }
    }
    @Override
    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
