package io.iamkyu.controller;

import io.iamkyu.support.AbstractAcceptanceTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-05-18
 */
public class HomeControllerTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(HomeControllerTest.class);

    @Test
    public void home() {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        logger.debug("body : {}", response.getBody());
    }
}
