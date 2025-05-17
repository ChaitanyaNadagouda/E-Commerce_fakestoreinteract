package com.fakestoreInteract.Ecommerce.models;


import com.fakestoreInteract.Ecommerce.models.BaseModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private Long panNo;
    private String address;
    private Long adhaarNo;

}
