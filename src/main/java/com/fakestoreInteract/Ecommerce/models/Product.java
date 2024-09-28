package com.fakestoreInteract.Ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private Category category;
    private String imageURL;
}
