package com.xier.sesame.attence.utils;

import javax.servlet.http.HttpSession;

public class SessionHolder {
    private static ThreadLocal<HttpSession> sessionThreadLocal = new ThreadLocal<HttpSession>() {

        @Override
        protected HttpSession initialValue() {
            return null;
        }

    };


    public static void remove() {
        sessionThreadLocal.remove();
    }


    public static void setSession(HttpSession session) {
        sessionThreadLocal.set(session);
    }


    public static HttpSession getSession() {
        return sessionThreadLocal.get();
    }
    
    public static Long getChooseOrgId() {
        return new Long(String.valueOf(sessionThreadLocal.get().getAttribute("chooseOrgId")));
    }
    
    public static void setChooseOrgId(Long orgId) {
        sessionThreadLocal.get().setAttribute("chooseOrgId", orgId);
    }
    
}
