package com.toys.ACB.handler;

import com.toys.ACB.response.Result;
import com.toys.ACB.response.ResultCode;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

@Component("myExpiredSessionStrategy")
public class MyExpiredSessionStrategy extends JsonWriter implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        Result result = Result.error(ResultCode.USER_ACCOUNT_USED_BY_OTHER);
        this.writeJSON(sessionInformationExpiredEvent.getRequest(), sessionInformationExpiredEvent.getResponse(), result);
    }
}
