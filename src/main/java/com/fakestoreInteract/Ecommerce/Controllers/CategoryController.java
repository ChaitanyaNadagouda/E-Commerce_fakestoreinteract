package com.fakestoreInteract.Ecommerce.Controllers;

import com.fakestoreInteract.Ecommerce.DTOs.CategoryDto;
import com.fakestoreInteract.Ecommerce.DTOs.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/category")
public class CategoryController implements com.fakestoreInteract.Ecommerce.Services.CategoryService {

    @Override
    @GetMapping()
    public String getAllCategories(){
        return "all categories";
    }

    @Override
    @GetMapping("/{categoryId}")
    public String getSingleCategory(@PathVariable("categoryId") Long categoryId){
        return "one category" + categoryId;
    }

    @Override
    @GetMapping("/{categoryId}")
    public String getAllProductsInCategory(@PathVariable("categoryid") Long categoryId){
        return "getting the products in category : " + categoryId ;
    }

    @Override
    @PostMapping()
    public String addSingleCategory(@RequestBody CategoryDto categoryDto){
        return "Adding single category";
    }

}
