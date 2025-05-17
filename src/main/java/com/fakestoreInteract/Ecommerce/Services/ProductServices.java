package com.fakestoreInteract.Ecommerce.Services;

import com.fakestoreInteract.Ecommerce.DTOs.ProductDto;
import com.fakestoreInteract.Ecommerce.DTOs.ProductRequestDto;
import com.fakestoreInteract.Ecommerce.Exceptions.InvalidProductException;
import com.fakestoreInteract.Ecommerce.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface ProductServices {

    List<Product> getAllProducts() throws InvalidProductException;


    Product getSingleProduct(Long productId) throws InvalidProductException;

    Product addNewProduct(ProductDto productDto) throws InvalidProductException;

    Product updateProduct(Long productId, ProductDto productDto) throws InvalidProductException;

    Product deleteProduct(Long productId) throws InvalidProductException;

    List<Product> getProductsByCategory(String categoryName) throws InvalidProductException;
}
