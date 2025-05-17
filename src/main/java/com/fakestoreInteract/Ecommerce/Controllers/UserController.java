package com.fakestoreInteract.Ecommerce.Controllers;


import com.fakestoreInteract.Ecommerce.DTOs.UserLoginRequestDto;
import com.fakestoreInteract.Ecommerce.DTOs.UserSignUpRequestDTO;
import com.fakestoreInteract.Ecommerce.Exceptions.PasswordDoesntMatchException;
import com.fakestoreInteract.Ecommerce.Exceptions.PasswordNullException;
import com.fakestoreInteract.Ecommerce.Exceptions.UserAlreadyExistsException;
import com.fakestoreInteract.Ecommerce.Exceptions.UserDoesntExistsException;
import com.fakestoreInteract.Ecommerce.Services.UserService;
import com.fakestoreInteract.Ecommerce.models.User;
import com.fakestoreInteract.Ecommerce.models.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> userSignUp(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) throws UserAlreadyExistsException {
        User user = userService.signUp(userSignUpRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserToken> loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto) throws PasswordNullException, UserDoesntExistsException, PasswordDoesntMatchException {
        UserToken userToken = userService.login(userLoginRequestDto);
        return new ResponseEntity<>(userToken,HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logoutUser(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        userService.logout(token);
    }

}
