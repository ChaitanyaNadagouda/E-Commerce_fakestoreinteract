package com.fakestoreInteract.Ecommerce.Services;

import com.fakestoreInteract.Ecommerce.DTOs.CategoryDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoryService {

    String getAllCategories();

    String getSingleCategory(@PathVariable("categoryId") Long categoryId);

    String getAllProductsInCategory(@PathVariable("categoryid") Long categoryId);

    String addSingleCategory(@RequestBody CategoryDto categoryDto);
}
