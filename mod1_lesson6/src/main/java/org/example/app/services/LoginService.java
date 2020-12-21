package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class);
    private final AuthRepository<LoginForm> authRepository;

    @Autowired
    LoginService(AuthRepository<LoginForm> authRepository) {
        this.authRepository = authRepository;
    }

    public boolean authenticate(LoginForm loginForm) {
        logger.info("try auth with user-form: " + loginForm);
        return authRepository.authenticate(loginForm);
    }

    public boolean register(LoginForm loginForm) {
        logger.info("try register with user-form: " + loginForm);
        if (authRepository.is_user_exist(loginForm)) {
            logger.warn("login already exists");
            return false;
        }

        authRepository.store(loginForm);

        return true;
    }
}
