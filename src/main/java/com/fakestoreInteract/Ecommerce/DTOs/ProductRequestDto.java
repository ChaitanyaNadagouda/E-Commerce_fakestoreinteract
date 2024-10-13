package com.fakestoreInteract.Ecommerce.DTOs;

import com.fakestoreInteract.Ecommerce.models.Category;
import com.fakestoreInteract.Ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    public Product getProductFromProductDto(ProductRequestDto productDtoBody) {
        Product product = new Product();
        Category category1 = new Category();
        category1.setName(productDtoBody.getCategory());
        product.setCategory(category1);
        product.setDescription(productDtoBody.getDescription());
        product.setTitle(productDtoBody.getTitle());
        product.setPrice(productDtoBody.getPrice());
        product.setImageURL(productDtoBody.getImage());
        return product;
    }
}
