package ru.itmo.wp.form.validator;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.RegisterUserCredentials;
import ru.itmo.wp.service.UserService;

@Component
public class UserCredentialsRegisterValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserCredentialsRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return RegisterUserCredentials.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@NotNull Object o, Errors errors) {
        if (!errors.hasErrors()) {
            RegisterUserCredentials registerUserCredentials = (RegisterUserCredentials) o;
            if (!registerUserCredentials.getPassword().equals(registerUserCredentials.getPasswordConfirmation())) {
                errors.rejectValue("passwordConfirmation", "passwordConfirmation.passwords-not-match", "Passwords are not equal");
            }
            if(userService.findByLogin(registerUserCredentials.getLogin()) != null) {
                errors.rejectValue("login", "login.is-used", "This login is already used");
            }
        }
    }
}