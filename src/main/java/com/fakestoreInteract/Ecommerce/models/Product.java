package com.fakestoreInteract.Ecommerce.models;

import com.fakestoreInteract.Ecommerce.DTOs.RatingDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    // p : c
    // 1 : 1
//     M  :  1
    @ManyToOne
    private Category category;
    private String imageURL;
    @Transient
    private RatingDto ratingDto ;
}
