package io.iamkyu.service;

import io.iamkyu.UnAuthenticationException;
import io.iamkyu.domain.User;
import io.iamkyu.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    public void login_success() throws Exception {
        User user = new User("sanjigi", "password", "name", "javajigi@slipp.net");
        when(userRepository.findByUserId(user.getUserId())).thenReturn(Optional.of(user));
        
        User loginUser = userService.login(user.getUserId(), user.getPassword());
        assertThat(loginUser, is(user));
    }
    
    @Test(expected = UnAuthenticationException.class)
    public void login_failed_when_user_not_found() throws Exception {
        when(userRepository.findByUserId("sanjigi")).thenReturn(Optional.empty());
        
        userService.login("sanjigi", "password");
    }
    
    @Test(expected = UnAuthenticationException.class)
    public void login_failed_when_mismatch_password() throws Exception {
        User user = new User("sanjigi", "password", "name", "javajigi@slipp.net");
        when(userRepository.findByUserId(user.getUserId())).thenReturn(Optional.of(user));
        
        userService.login(user.getUserId(), user.getPassword() + "2");
    }
}
