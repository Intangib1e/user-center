package com.llj.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 刘露杰
 * @Date 2023/5/28 13:53
 * @Description: 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 1424412394009355267L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
