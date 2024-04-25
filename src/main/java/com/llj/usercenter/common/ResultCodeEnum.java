package com.llj.usercenter.common;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功", "请求成功"),
    FAIL(201, "失败", "请求失败"),
    SERVICE_ERROR(2012, "服务异常", "服务异常"),
    SYS_ERROR(500,"系统异常", "系统异常"),
    DATA_ERROR(204, "数据异常", "数据异常"),
    ILLEGAL_REQUEST(205, "非法请求", "非法请求"),
    REPEAT_SUBMIT(206, "重复提交", "重复提交"),
    ARGUMENT_VALID_ERROR(210, "参数校验异常", "参数校验异常"),

    LOGIN_AUTH(208, "未登陆", "未登陆"),
    PERMISSION(209, "没有权限", "没有权限"),
    ACCOUNT_ERROR(214, "账号不正确", "账号不正确"),
    ACCOUNT_EXIST(215,"账号已存在", "账号已存在"),
    PASSWORD_ERROR(216, "密码不正确", "密码不正确"),
    LOGIN_MOBLE_ERROR( 217, "账号或密码不正确", "账号或密码不正确"),
    ACCOUNT_STOP( 218, "账号已停用", "账号已停用"),
    NODE_ERROR( 219, "该节点下有子节点，不可以删除", "该节点下有子节点，不可以删除")
    ;

    private final Integer code;

    private final String message;

    private final String description;

    private ResultCodeEnum(Integer code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description =  description;
    }
}
