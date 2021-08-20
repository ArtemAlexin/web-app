package ru.itmo.wp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.form.RegisterUserCredentials;
import ru.itmo.wp.domain.DTO.UserDTO;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.validator.UserCredentialsRegisterValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserRegisterService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/1")
public class UserController {
    private final JwtService jwtService;
    private final UserRegisterService userRegisterService;
    private final UserService userService;
    private final UserCredentialsRegisterValidator validator;
    @Autowired
    public UserController(JwtService jwtService, UserRegisterService userRegisterService,
                          UserService userService, UserCredentialsRegisterValidator validator) {
        this.jwtService = jwtService;
        this.userRegisterService = userRegisterService;
        this.userService = userService;
        this.validator = validator;
    }
    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
    }
    @GetMapping("/users/auth")
    public User findUserByJwt(@RequestParam String jwt) {
        return jwtService.find(jwt);
    }
    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }
    @PostMapping("/users")
    public String registerUser(@RequestBody @Valid RegisterUserCredentials userCredentials,
                                BindingResult result) {
        if(result.hasErrors()) {
            throw new ValidationException(result);
        }
        return userRegisterService.register(userCredentials);
    }
}
