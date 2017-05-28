package support.test;

import io.iamkyu.domain.User;
import io.iamkyu.domain.UserRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public abstract class BasicAuthAcceptanceTest extends AcceptanceTest {
    protected TestRestTemplate basicAuthTemplate;

    @Autowired
    private UserRepository userRepository;

    protected User loginUser;

    @Before
    public void setup() {
        loginUser = userRepository.findByUserId("javajigi").get();
        basicAuthTemplate = template.withBasicAuth(loginUser.getUserId(), loginUser.getPassword());
    }
}
