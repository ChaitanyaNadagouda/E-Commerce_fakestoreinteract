package com.fakestoreInteract.Ecommerce.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailMessageDto {
    private String from;
    private String to;
    private String subject;
    private String body;
}
