package com.llj.usercenter.exception;

import com.llj.usercenter.common.Result;
import com.llj.usercenter.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 刘露杰
 * @Date 2024/3/9 18:13
 * @Description: 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<?> businessException(BusinessException e){
        log.error("businessException" + e.getDescription(), e);
        return Result.fail(ResultCodeEnum.SYS_ERROR.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeException(RuntimeException e){
        log.error("runtimeException", e);
        return Result.fail(ResultCodeEnum.SYS_ERROR.getCode(), e.getMessage(), "");
    }
}
