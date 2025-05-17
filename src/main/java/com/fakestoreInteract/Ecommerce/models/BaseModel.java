package com.fakestoreInteract.Ecommerce.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.security.PrivateKey;
import java.util.Date;
@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdOn;
    @LastModifiedDate
    @UpdateTimestamp
    private Date updatedOn;
    private boolean isDeleted;



}
