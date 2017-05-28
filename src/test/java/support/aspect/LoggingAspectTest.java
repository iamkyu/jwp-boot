package support.aspect;

import io.iamkyu.JwpSpringBootApplication;
import io.iamkyu.domain.User;
import io.iamkyu.web.LoginController;
import io.iamkyu.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwpSpringBootApplication.class)
public class LoggingAspectTest {
    @Autowired
    private UserController userController;

    @Autowired
    private LoginController loginController;

    @Test
    public void logging() {
        User user = new User("aspectuser", "password", "name2", "javajigi@slipp.net2");
        userController.create(user);

        MockHttpSession session = new MockHttpSession();
        loginController.login(user.getUserId(), user.getPassword(), session);
    }

}
