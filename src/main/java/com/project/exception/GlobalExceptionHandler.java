package com.project.exception;

import com.project.dto.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Result handlerException(HttpServletRequest request, HttpServletResponse response, Exception e)
    {
        if (e instanceof CustomException) {
            return Result.error(((CustomException) e).getMsg());
        } else {
            return Result.error(e.getMessage());
        }
    }
}
