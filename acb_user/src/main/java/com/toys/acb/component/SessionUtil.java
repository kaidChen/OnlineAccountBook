package com.toys.acb.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class SessionUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(SessionUtil.class);

    private Map<String, HttpSession> map;

    private void setSession(String username, HttpSession session) {
        LOGGER.info("用户登录成功：username={}, sessionId={}", username, session.getId());
        map.put(username, session);
    }

    private void deleteSession(String username) {
        HttpSession session = map.getOrDefault(username, null);
        if (session != null) {
            LOGGER.info("用户被挤占下线：sessionId={}", session.getId());
            session.invalidate();
        }
    }

    public synchronized void login(String username, HttpSession session) {
        if (map == null) map = new HashMap<>();
        deleteSession(username);
        setSession(username, session);
    }
}
