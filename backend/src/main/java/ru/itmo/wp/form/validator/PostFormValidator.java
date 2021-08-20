package ru.itmo.wp.form.validator;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.PostService;

@Component
public class PostFormValidator implements Validator {
    private final JwtService jwtService;
    @Autowired
    public PostFormValidator(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return PostForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@NotNull Object o, Errors errors) {
        if(errors.hasErrors()) {
            PostForm postForm = (PostForm) o;
            if (jwtService.find(postForm.getJwt()) != null) {
                errors.rejectValue("jwt", "jwt.not-valid", "Jwt token is invalid");
            }
        }
    }
}
