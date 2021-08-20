package ru.itmo.wp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.RegisterUserCredentials;
import ru.itmo.wp.repository.UserRepository;

@Service
public class UserRegisterService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public UserRegisterService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    public String register(RegisterUserCredentials userCredentials) {
        User user = userRepository.save(userCredentials.MapToUser());
        userRepository.updatePasswordSha(user.getId(), user.getLogin(), userCredentials.getPassword());
        user = userRepository.findByLogin(user.getLogin());
        return jwtService.create(user);
    }
}
