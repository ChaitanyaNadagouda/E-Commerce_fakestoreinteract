package com.fakestoreInteract.Ecommerce.Services;

import com.fakestoreInteract.Ecommerce.DTOs.UserLoginRequestDto;
import com.fakestoreInteract.Ecommerce.DTOs.UserSignUpRequestDTO;
import com.fakestoreInteract.Ecommerce.Exceptions.PasswordDoesntMatchException;
import com.fakestoreInteract.Ecommerce.Exceptions.PasswordNullException;
import com.fakestoreInteract.Ecommerce.Exceptions.UserAlreadyExistsException;
import com.fakestoreInteract.Ecommerce.Exceptions.UserDoesntExistsException;
import com.fakestoreInteract.Ecommerce.Repositories.UserRepository;
import com.fakestoreInteract.Ecommerce.Repositories.UserTokenReporsitory;
import com.fakestoreInteract.Ecommerce.models.User;
import com.fakestoreInteract.Ecommerce.models.UserToken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserTokenReporsitory userTokenReporsitory;

    public User signUp(UserSignUpRequestDTO userSignUpRequestDTO) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByEmail(userSignUpRequestDTO.getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = getUserFromUserRequestDTO(userSignUpRequestDTO);
        return userRepository.save(user);
    }

    public UserToken login(UserLoginRequestDto userLoginRequestDto)
            throws PasswordNullException, UserDoesntExistsException, PasswordDoesntMatchException {

        // Step 1: Validate password
        String enteredPassword = userLoginRequestDto.getPassword();
        if (enteredPassword == null || enteredPassword.isEmpty()) {
            throw new PasswordNullException("Password cannot be null or empty");
        }

        // Step 2: Check user existence
        Optional<User> userOptional = userRepository.findByEmail(userLoginRequestDto.getEmail());
        if (!userOptional.isPresent()) {
            throw new UserDoesntExistsException("User does not exist");
        }

        User user = userOptional.get();

        // Step 3: Verify password
        if (!bCryptPasswordEncoder.matches(enteredPassword, user.getPassword())) {
            throw new PasswordDoesntMatchException("Password doesn't match");
        }

        // Step 4: Return token
        return handleUserToken(user);
    }

    public void logout(String token) {
       Optional<UserToken> userTokenOptional = userTokenReporsitory.findByTokenAndIsDeleted(token,false);

        if (!userTokenOptional.isPresent()) {
            throw new RuntimeException("Invalid token");
        }

        UserToken tokenObject = userTokenOptional.get();
        tokenObject.setDeleted(true);

        userTokenReporsitory.save(tokenObject);
    }

    private UserToken handleUserToken(User user) {
        Optional<UserToken> userTokenOptional = userTokenReporsitory.findByUserId(user.getId());
        UserToken userToken;

        if (userTokenOptional.isPresent()) {
            userToken = userTokenOptional.get();

            if (userToken.isDeleted()) {
                userToken.setDeleted(false);
                updateToken(userToken);
            } else if (userToken.getValidUpto().before(new Date())) {
                updateToken(userToken);
            }
        } else {
            userToken = createNewToken(user);
        }

        return userTokenReporsitory.save(userToken);
    }

    private UserToken createNewToken(User user) {
        UserToken userToken = new UserToken();
        userToken.setUser(user);
        updateToken(userToken);
        return userToken;
    }

    private void updateToken(UserToken userToken) {
        userToken.setToken(UUID.randomUUID().toString());
        userToken.setValidUpto(setExpiryValue());
        userToken.setDeleted(false);
    }

    private Date setExpiryValue() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 5); // 5 days validity
        return calendar.getTime();
    }

    private User getUserFromUserRequestDTO(UserSignUpRequestDTO userSignUpRequestDTO) {
        User user = new User();
        user.setEmail(userSignUpRequestDTO.getEmail());
        user.setFirstName(userSignUpRequestDTO.getFirstName());
        user.setMiddleName(userSignUpRequestDTO.getMiddleName());
        user.setLastName(userSignUpRequestDTO.getLastName());
        user.setAddress(userSignUpRequestDTO.getAddress());
        user.setAdhaarNo(userSignUpRequestDTO.getAdhaarNo());
        user.setPanNo(userSignUpRequestDTO.getPanNo());

        String encodedPassword = bCryptPasswordEncoder.encode(userSignUpRequestDTO.getPassword());
        user.setPassword(encodedPassword);

        return user;
    }

}
