package io.iamkyu.controller;

import io.iamkyu.model.User;
import io.iamkyu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Kj Nam
 * @since 2017-05-22
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired UserService userService;

    @GetMapping("")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("")
    public String login(String userId, String password, HttpSession session) throws IllegalAccessException {
        try {
            User user = userService.login(userId, password);
            session.setAttribute("loginedUser", user);
            return "redirect:/";
        } catch (IllegalStateException e) {
            return "/user/login_failed";
        }
    }
}
