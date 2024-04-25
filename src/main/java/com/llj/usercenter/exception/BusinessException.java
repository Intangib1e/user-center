package com.llj.usercenter.exception;

import com.llj.usercenter.common.ResultCodeEnum;

/**
 * @Author 刘露杰
 * @Date 2024/3/9 17:56
 * @Description: 自定义异常类
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -858480130239355355L;

    private final int code;

    private final String description;


    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.description = resultCodeEnum.getDescription();
    }

    public BusinessException(ResultCodeEnum resultCodeEnum, String description) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
