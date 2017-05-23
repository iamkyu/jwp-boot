package io.iamkyu.controller;

import io.iamkyu.infrastructure.UserRepository;
import io.iamkyu.model.User;
import io.iamkyu.support.AbstractAcceptanceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-05-22
 */
public class LoginControllerTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @Before
    public void setUp() {
        testUser = userRepository.save(new User("iamkyu", "pass", "name", "a@b.c"));
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void loginForm() {
        //given when
        ResponseEntity<String> response = template
                .getForEntity("/login", String.class);

        //then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void login() {
        //given
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("userId", "iamkyu");
        params.add("password", "pass");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, getFormHttpHeaders());

        //when
        ResponseEntity<String> response = template.postForEntity("/login", request, String.class);

        //then
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
        assertTrue(response.getHeaders().getLocation().getPath().startsWith("/;jsessionid="));
    }

    private HttpHeaders getFormHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
