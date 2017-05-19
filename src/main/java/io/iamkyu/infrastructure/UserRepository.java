package io.iamkyu.infrastructure;

import io.iamkyu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2017-05-18
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
