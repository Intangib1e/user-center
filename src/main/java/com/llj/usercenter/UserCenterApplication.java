package com.llj.usercenter;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.llj.usercenter.mapper")
//@EnableGracefulResponse// 开启统一结果返回
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}
