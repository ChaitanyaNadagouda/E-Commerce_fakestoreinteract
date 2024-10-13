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

//    @Override
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws InvalidProductException {
        List<Product> productList = new ArrayList<>();
        ResponseEntity<List<Product>> responseList;
        try {
            productList = fakestoreService.getAllProducts();
            responseList = new ResponseEntity<>(productList,HttpStatus.OK);
        } catch (InvalidProductException e){
            responseList = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return responseList;
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductWrapper> getSingleProduct(@PathVariable("productId") Long productId) throws InvalidProductException{

        /*
        Product singleProduct ;
        ProductWrapper productWrapper ;
        ResponseEntity<ProductWrapper> response ;
        */
        Product singleProduct = fakestoreService.getSingleProduct(productId);
        ProductWrapper productWrapper = new ProductWrapper(singleProduct,"Successfully retrieved product");
        ResponseEntity<ProductWrapper> response = new ResponseEntity<>(productWrapper,HttpStatus.OK);

        //another way to handle exception :
        /*
        try {
            singleProduct = fakestoreService.getSingleProduct(productId);
            productWrapper = new ProductWrapper(singleProduct,"Successfully retrieved product");
            response = new ResponseEntity<>(productWrapper,HttpStatus.OK);
        }catch (InvalidProductException e){
            productWrapper = new ProductWrapper(null,"Product not found");
            response = new ResponseEntity<>(productWrapper,HttpStatus.NOT_FOUND);
        }
         */

        return response;
    }


    @PostMapping()
    public ResponseEntity<ProductWrapper> addNewProduct(@RequestBody ProductDto productDto) throws InvalidProductException {

        Product product;
        ProductWrapper productWrapper;
        ResponseEntity<ProductWrapper> response;

        try {
            product = fakestoreService.addNewProduct(productDto);
            productWrapper = new ProductWrapper(product,"Successfully added the product");
            response = new ResponseEntity<>(productWrapper,HttpStatus.OK);
        } catch (InvalidProductException e){
            productWrapper = new ProductWrapper(null,"Couldnt Add the given product");
            response = new ResponseEntity<>(productWrapper,HttpStatus.NOT_FOUND);
        }

        return response;
    }


    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        return "updating single product with id : " + productId ;
    }


    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long productId) throws InvalidProductException {
        return new ResponseEntity<>(fakestoreService.deleteProduct(productId),HttpStatus.OK);
    }

}
