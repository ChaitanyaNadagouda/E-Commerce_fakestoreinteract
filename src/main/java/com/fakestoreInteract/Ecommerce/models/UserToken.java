package com.fakestoreInteract.Ecommerce.models;

import com.fakestoreInteract.Ecommerce.models.BaseModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class UserToken extends BaseModel {

    @OneToOne
    @JoinColumn
    private User user;
    private String token;
    private Date validUpto;
    private boolean isDeleted;

}
