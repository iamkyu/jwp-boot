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
 * @since 2017-05-18
 */
public class UserControllerTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired private UserRepository userRepository;

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
    public void create() {
        //given
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(getUserParam(), getFormHttpHeaders());

        //when
        ResponseEntity<String> response = template.postForEntity("/users", request, String.class);

        //then
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
    }

    @Test
    public void updateForm() {
        //given when
        ResponseEntity<String> response = template
                .getForEntity(String.format("/users/%d/form", testUser.getId()), String.class);

        //then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().contains(testUser.getEmail()), is(true));
    }

    @Test
    public void update() {
        //given
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("_method", "put");
        params.add("password", "pass2");
        params.add("name", "name2");
        params.add("email", "a@b.d");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, getFormHttpHeaders());

        //when
        ResponseEntity<String> response = template
                .postForEntity(String.format("/users/%d", testUser.getId()), request, String.class);

        //then
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
        assertThat(response.getHeaders().getLocation().getPath(), is("/users"));
    }

    @Test
    public void list() {
        //given when
        ResponseEntity<String> response = template.getForEntity("/users", String.class);

        //then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        logger.debug("body : {}", response.getBody());
    }

    private HttpHeaders getFormHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private MultiValueMap<String, Object> getUserParam() {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("userId", "tester");
        params.add("password", "password");
        params.add("name", "kyukyu");
        params.add("email", "a@b.c");

        return params;
    }
}
