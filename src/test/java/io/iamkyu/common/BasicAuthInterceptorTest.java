package io.iamkyu.common;

import io.iamkyu.common.util.HttpSessionUtils;
import io.iamkyu.model.User;
import io.iamkyu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Base64;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author Kj Nam
 * @since 2017-05-24
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicAuthInterceptorTest {
    @Mock private UserService userService;
    @InjectMocks private BasicAuthInterceptor basicAuthInterceptor;

    @Test
    public void interceptTest() throws Exception {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        String encodedBasicAuth = Base64.getEncoder().encodeToString("iamkyu:password".getBytes());
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);

        User anUser = new User("iamkyu", "password", "name", "email");
        when(userService.login("iamkyu", "password")).thenReturn(anUser);

        //when
        basicAuthInterceptor.preHandle(request, null, null);

        //then
        assertThat(request.getSession().getAttribute(HttpSessionUtils.USER_SESSION_KEY), is(anUser));
    }
}