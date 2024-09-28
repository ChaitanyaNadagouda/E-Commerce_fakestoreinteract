package com.fakestoreInteract.Ecommerce.models;

import lombok.*;

import java.security.PrivateKey;
import java.util.Date;
@Getter
@Setter
public class BaseModel {
    private Long id;
    private Date createdOn;
    private Date updatedOn;
    private boolean isDeleted;



}
