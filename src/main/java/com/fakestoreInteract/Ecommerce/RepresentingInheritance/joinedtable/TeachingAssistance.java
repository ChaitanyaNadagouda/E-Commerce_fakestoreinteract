package com.fakestoreInteract.Ecommerce.RepresentingInheritance.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_TA")
@PrimaryKeyJoinColumn(name = "user_id")
public class TeachingAssistance extends User {

    private String name;
    private String moduletaken;

}
