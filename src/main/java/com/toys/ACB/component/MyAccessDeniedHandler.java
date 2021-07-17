package com.toys.ACB.component;

import com.toys.ACB.response.Result;
import com.toys.ACB.response.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAccessDeniedHandler")
public class MyAccessDeniedHandler extends JsonWriter implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Result result = Result.error(ResultCode.NO_PERMISSION);
        this.writeJSON(httpServletRequest, httpServletResponse, result);
    }
}
