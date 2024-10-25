package com.ijse.database.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.ijse.database.dto.ProductDTO;
import com.ijse.database.entity.Product;
import com.ijse.database.service.ProductService;

@RestController
@CrossOrigin(origins = "*") //allowing cross origin to all
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(201).body(productService.createProduct(productDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create the Product");
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product); //Return 200 with Body
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //Return 404
        }
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @GetMapping("/categories/{id}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            boolean deleted = productService.deleteProduct(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the product");
        }
    }
}
