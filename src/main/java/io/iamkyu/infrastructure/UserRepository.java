package io.iamkyu.infrastructure;

import io.iamkyu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Kj Nam
 * @since 2017-05-18
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
}
