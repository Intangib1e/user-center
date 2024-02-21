package com.llj.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 刘露杰
 * @Date 2023/5/28 13:53
 * @Description: 用户登录请求体
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 1646302151374103904L;

    private String userAccount;

    private String userPassword;

}
