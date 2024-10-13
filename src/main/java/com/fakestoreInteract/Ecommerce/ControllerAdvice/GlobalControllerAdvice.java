package com.fakestoreInteract.Ecommerce.ControllerAdvice;


import com.fakestoreInteract.Ecommerce.DTOs.ErrorResponseDTO;
import com.fakestoreInteract.Ecommerce.Exceptions.InvalidProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidProductException(){
        return new ResponseEntity<>(new ErrorResponseDTO("Invalid product"), HttpStatus.NOT_FOUND);
    }
}
