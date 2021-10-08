package com.toys.acb.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionUtil {

    private Map<String, HttpSession> map;

    private void setSession(String username, HttpSession session) {
        map.put(username, session);
    }

    private void deleteSession(String username) {
        HttpSession session = map.getOrDefault(username, null);
        if (session != null) {
            session.invalidate();
        }
    }

    public synchronized void login(String username, HttpSession session) {
        if (map == null) map = new HashMap<>();
        deleteSession(username);
        setSession(username, session);
    }
}
