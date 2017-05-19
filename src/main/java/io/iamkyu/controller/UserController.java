package io.iamkyu.controller;

import io.iamkyu.infrastructure.UserRepository;
import io.iamkyu.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-18
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired UserRepository userRepository;

    @GetMapping("/users")
    public String list(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "/user/list";
    }

    @PostMapping("/users")
    public String create(User user) {
        logger.debug("User: {}", user);
        userRepository.save(user);

        return "redirect:/";
    }
}
