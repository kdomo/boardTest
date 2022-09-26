package com.domo.boardtest.utils;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

public class SessionUtil {
    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";

    private SessionUtil() {
    }

    public static void setLoginId(HttpSession session, Long id) {
        session.setAttribute(LOGIN_USER_ID, id);
    }

    public static Long getLoginId(HttpSession session) {
        return (Long) session.getAttribute(LOGIN_USER_ID);
    }

    public static void logout(HttpSession session) {
        session.removeAttribute(LOGIN_USER_ID);
    }

}
