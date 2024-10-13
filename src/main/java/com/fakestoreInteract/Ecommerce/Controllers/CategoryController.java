package com.fakestoreInteract.Ecommerce.Controllers;

import com.fakestoreInteract.Ecommerce.DTOs.CategoryDto;
import com.fakestoreInteract.Ecommerce.DTOs.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/category")
public class CategoryController {

    @GetMapping()
    public String getAllCategories(){
        return "all categories";
    }

    @GetMapping("/{categoryId}")
    public String getSingleCategory(@PathVariable("categoryId") Long categoryId){
        return "one category" + categoryId;
    }

    @GetMapping("/{categoryId}/products")
    public String getAllProductsInCategory(@PathVariable("categoryId") Long categoryId){
        return "getting the products in category : " + categoryId ;
    }

    @PostMapping()
    public String addSingleCategory(@RequestBody CategoryDto categoryDto){
        return "Adding single category";
    }

}
