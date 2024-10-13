package com.fakestoreInteract.Ecommerce.RepresentingInheritance.joinedtable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "jt_user")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

}
