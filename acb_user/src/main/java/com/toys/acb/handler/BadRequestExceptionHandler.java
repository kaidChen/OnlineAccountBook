package com.toys.acb.handler;

import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class BadRequestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> validationBodyException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        return new Result<>().error(ResultCode.PARAM_NOT_VALID).message(allErrors.get(allErrors.size() - 1).getDefaultMessage());
    }
}
