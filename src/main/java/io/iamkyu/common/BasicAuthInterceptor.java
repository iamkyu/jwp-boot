package io.iamkyu.common;

import io.iamkyu.model.User;
import io.iamkyu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author Kj Nam
 * @since 2017-05-24
 */
public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(BasicAuthInterceptor.class);

    @Autowired private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        logger.debug("Authorization : {}", authorization);
        if (authorization == null || !authorization.startsWith("Basic")) {
            return true;
        }

        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));

        String[] userInfo = credentials.split(":");
        logger.debug("User: {}, {}", userInfo[0], userInfo[1]);


        User user = null;
        try {
            user = userService.login(userInfo[0], userInfo[1]);
        } catch (IllegalStateException e) {
            throw new UnAuthorizedException();
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginedUser", user);
        return true;
    }
}
