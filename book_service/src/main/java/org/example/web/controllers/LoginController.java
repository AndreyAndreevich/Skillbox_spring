package org.example.web.controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.example.app.exceptions.BookShelfLoginException;
import org.example.app.services.LoginService;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final Logger logger = Logger.getLogger(LoginController.class);
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model) {
        logger.info("GET /login returns login_page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }

    @PostMapping("/auth")
    public String authenticate(@Valid LoginForm loginForm, BindingResult result) throws BookShelfLoginException {
        if (result.hasErrors()) {
            logger.warn(result.getAllErrors());
            return "redirect:/login";
        }

        if (loginService.authenticate(loginForm)) {
            logger.info("login OK redirect to book shelf");
            return "redirect:/books/shelf";
        } else {
            logger.info("login FAIL redirect back to login");
            throw new BookShelfLoginException("invalid username or password");
        }
    }

    @PostMapping("/register")
    public String register(@Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn(result.getAllErrors());
            return "redirect:/login";
        }

        if (loginService.register(loginForm)) {
            logger.info("register OK redirect to book shelf");
            return "redirect:/books/shelf";
        } else {
            logger.info("register FAIL redirect back to login");
            return "redirect:/login";
        }
    }

    @ExceptionHandler(BookShelfLoginException.class)
    public String handleError(Model model, BookShelfLoginException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/404";
    }
}
