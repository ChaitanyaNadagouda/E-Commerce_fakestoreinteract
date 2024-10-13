package com.fakestoreInteract.Ecommerce.DTOs;

import com.fakestoreInteract.Ecommerce.Exceptions.InvalidProductException;
import com.fakestoreInteract.Ecommerce.models.Category;
import com.fakestoreInteract.Ecommerce.models.Product;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class ProductDto {
    @Nullable
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    @Nullable
    private RatingDto rating;

    public Product getProductFromProductDto(ProductDto productDto)  {
        Product product = new Product();
        product.setId(productDto.getId());
        Category category1 = new Category();
        category1.setName(productDto.getCategory());
        product.setCategory(category1);
        product.setDescription(productDto.getDescription());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageURL(productDto.getImage());
        product.setRatingDto(productDto.getRating());
//        product.setDeleted(isDeleted);
        return product;
    }
}
