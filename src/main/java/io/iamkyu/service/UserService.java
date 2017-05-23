package io.iamkyu.service;

import io.iamkyu.infrastructure.UserRepository;
import io.iamkyu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Kj Nam
 * @since 2017-05-23
 */
@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public User login(String userId, String password) {
        Optional<User> maybeUser = userRepository.findByUserId(userId);
        if (!maybeUser.isPresent()) {
            throw new IllegalStateException();
        }

        User user = maybeUser.get();
        if (!user.matchPassword(password)) {
            throw new IllegalStateException();
        }

        return user;
    }
}
