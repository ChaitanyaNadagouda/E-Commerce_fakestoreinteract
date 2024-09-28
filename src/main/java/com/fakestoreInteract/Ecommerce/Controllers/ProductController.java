package com.fakestoreInteract.Ecommerce.Controllers;

import com.fakestoreInteract.Ecommerce.DTOs.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController{

//    @Override
    @GetMapping()
    public String getAllProducts(){
        return "All products";
    }


    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "one product with id: " + productId;
    }


    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "adding new product : " + productDto;
    }


    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        return "updating single product with id : " + productId ;
    }


    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        return "deleting a product : " + productId;
    }

}
