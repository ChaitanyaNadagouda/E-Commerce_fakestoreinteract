package com.fakestoreInteract.Ecommerce.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long panNo;
    private String address;
    private Long adhaarNo;
}
