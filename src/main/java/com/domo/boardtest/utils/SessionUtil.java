package com.domo.boardtest.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";

    private SessionUtil() {
    }

    public static void setLoginId(HttpSession session, Long id) {
        session.setAttribute(LOGIN_MEMBER_ID, id);
    }

    public static Long getLoginId(HttpSession session) {
        return (Long) session.getAttribute(LOGIN_MEMBER_ID);
    }

}
