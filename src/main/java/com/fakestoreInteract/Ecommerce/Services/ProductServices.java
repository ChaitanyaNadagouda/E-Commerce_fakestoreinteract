package com.fakestoreInteract.Ecommerce.Services;

import com.fakestoreInteract.Ecommerce.DTOs.ProductDto;
import org.springframework.web.bind.annotation.*;

public interface ProductServices {
    @GetMapping()
    String getAllProducts();

    @GetMapping("/{productId}")
    String getSingleProduct(@PathVariable("productId") Long productId);

    @PostMapping()
    String addNewProduct(@RequestBody ProductDto productDto);

    @PutMapping("/{productId}")
    String updateProduct(@PathVariable("productId") Long productId);

    @DeleteMapping("/{productId}")
    String deleteProduct(@PathVariable Long productId);
}
