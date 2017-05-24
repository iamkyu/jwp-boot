package io.iamkyu.common.util;

import io.iamkyu.model.User;

import javax.servlet.http.HttpSession;

/**
 * @author Kj Nam
 * @since 2017-05-24
 */
public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static boolean isLoginUser(HttpSession session) {
        Object sessionUser = session.getAttribute(USER_SESSION_KEY);
        if (sessionUser == null) {
            return false;
        }
        return true;
    }

    public static User getUserFromSession(HttpSession session) {
        if (!isLoginUser(session)) {
            return null;
        }

        return (User)session.getAttribute(USER_SESSION_KEY);
    }
}
