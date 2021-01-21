package org.example.app.repository;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository implements AuthRepository<LoginForm> {

    private final Logger logger = Logger.getLogger(LoginRepository.class);
    private final ArrayList<LoginForm> repo;

    LoginRepository() {
        repo = new ArrayList<>();
        repo.add(new LoginForm("root", "123"));
    }

    @Override
    public boolean authenticate(LoginForm loginForm) {
        return repo.contains(loginForm);
    }

    @Override
    public boolean is_user_exist(LoginForm loginForm) {
        return repo.stream().anyMatch(current -> current.getUsername().equals(loginForm.getUsername()));
    }

    @Override
    public void store(LoginForm loginForm) {
        repo.add(loginForm);
        logger.info("store new user: " + loginForm);
    }
}
