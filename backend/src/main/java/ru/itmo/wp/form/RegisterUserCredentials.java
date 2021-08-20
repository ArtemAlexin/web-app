package ru.itmo.wp.form;

import ru.itmo.wp.domain.User;

import javax.annotation.MatchesPattern;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserCredentials {
    @Size(min = 1, max = 40)
    private String name;

    @Size(min = 5, max = 20)
    @Pattern(regexp = "[a-zA-Z]{2,24}", message = "login must contain only latin letters")
    private String login;

    @Size(min = 1, max = 15)
    @Pattern(regexp = "[a-zA-Z1-9_!%$]+", message = "password may contain only latin letters ot special signs: !, %, $, _")
    private String password;

    private String passwordConfirmation;

    public RegisterUserCredentials() {
    }

    public User MapToUser() {
        User user = new User();
        user.setAdmin(false);
        user.setLogin(login);
        user.setName(name);
        return user;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
