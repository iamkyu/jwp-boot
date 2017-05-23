package io.iamkyu.controller;

import io.iamkyu.infrastructure.UserRepository;
import io.iamkyu.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Kj Nam
 * @since 2017-05-18
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String list(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "/user/list";
    }

    @PostMapping("")
    public String create(User user) {
        logger.debug("User: {}", user);
        userRepository.save(user);

        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable long id, User user) {
        User anUser = userRepository.findOne(id);
        anUser.update(user);

        return "redirect:/users";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);

        return "/user/updateForm";
    }
}
