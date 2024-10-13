//package com.fakestoreInteract.Ecommerce.DTOs;
//
//import com.fakestoreInteract.Ecommerce.models.Category;
//import com.fakestoreInteract.Ecommerce.models.Product;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class ProductResponseDto {
//    private Long id;
//    private String title;
//    private double price;
//    private String description;
//    private String image;
//    private String category;
//
//    public Product getProductFromProductResponseDto(ProductRequestDto productRequestDto) {
//        Product product = new Product();
//        Category category1 = new Category();
//        category1.setName(productRequestDto.getCategory());
//        product.setCategory(category1);
//        product.setId(productRequestDto.getId());
//        product.setDescription(productRequestDto.getDescription());
//        product.setTitle(productRequestDto.getTitle());
//        product.setPrice(productRequestDto.getPrice());
//        product.setImageURL(productRequestDto.getImage());
//        return product;
//    }
//}
