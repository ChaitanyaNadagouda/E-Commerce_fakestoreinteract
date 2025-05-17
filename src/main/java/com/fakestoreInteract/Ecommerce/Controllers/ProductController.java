package com.fakestoreInteract.Ecommerce.Controllers;

import com.fakestoreInteract.Ecommerce.DTOs.ProductDto;
import com.fakestoreInteract.Ecommerce.DTOs.ProductRequestDto;
import com.fakestoreInteract.Ecommerce.DTOs.ProductWrapper;
import com.fakestoreInteract.Ecommerce.Exceptions.InvalidProductException;
import com.fakestoreInteract.Ecommerce.Services.FakestoreServiceImpl;
import com.fakestoreInteract.Ecommerce.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{

    @Autowired
    private FakestoreServiceImpl fakestoreService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> productList = fakestoreService.getAllProducts();
            return ResponseEntity.ok(productList);
        } catch (InvalidProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductWrapper> getSingleProduct(@PathVariable("productId") Long productId) {
        try {
            Product singleProduct = fakestoreService.getSingleProduct(productId);
            ProductWrapper productWrapper = new ProductWrapper(singleProduct, "Successfully retrieved product");
            return ResponseEntity.ok(productWrapper);
        } catch (InvalidProductException e) {
            return new ResponseEntity<>(new ProductWrapper(null, "Product not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<ProductWrapper> addNewProduct(@RequestBody ProductDto productDto) {
        try {
            Product product = fakestoreService.addNewProduct(productDto);
            ProductWrapper productWrapper = new ProductWrapper(product, "Successfully added the product");
            return ResponseEntity.status(HttpStatus.CREATED).body(productWrapper);
        } catch (InvalidProductException e) {
            return new ResponseEntity<>(new ProductWrapper(null, "Couldn't add the given product"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductWrapper> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) {
        try {
            Product updatedProduct = fakestoreService.updateProduct(productId, productDto);
            ProductWrapper productWrapper = new ProductWrapper(updatedProduct, "Successfully updated the product");
            return ResponseEntity.ok(productWrapper);
        } catch (InvalidProductException e) {
            return new ResponseEntity<>(new ProductWrapper(null, "Failed to update product"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductWrapper> deleteProduct(@PathVariable Long productId) {
        try {
            Product deletedProduct = fakestoreService.deleteProduct(productId);
            ProductWrapper productWrapper = new ProductWrapper(deletedProduct, "Successfully deleted the product");
            return ResponseEntity.ok(productWrapper);
        } catch (InvalidProductException e) {
            return new ResponseEntity<>(new ProductWrapper(null, "Product not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        try {
            List<Product> productList = fakestoreService.getProductsByCategory(categoryName);
            return ResponseEntity.ok(productList);
        } catch (InvalidProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
