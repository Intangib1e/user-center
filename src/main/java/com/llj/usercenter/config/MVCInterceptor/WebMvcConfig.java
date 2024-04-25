//package com.llj.usercenter.config.MVCInterceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @Author 刘露杰
// * @Date 2024/4/3 15:08
// * @Description:
// */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 设置允许跨域的路径
//        CorsRegistration corsRegistration = registry.addMapping("/**");
//        // 设置允许跨域请求的域名
//        // 当**Credentials为true时，**Origin不能为星号，需为具体的ip地址【如果接口不带cookie,ip无需设成具体ip】
////        corsRegistration.allowedOrigins("*");
//        // 所以解决办法：将.allowedOrigins替换成.allowedOriginPatterns即可。
//        corsRegistration.allowedOriginPatterns("*");
//        // 是否允许证书 不再默认开启
//        corsRegistration.allowCredentials(true);
//        // 设置允许的方法
//        corsRegistration.allowedMethods("*");
//        // 跨域允许时间
//        corsRegistration.maxAge(3600);
//        corsRegistration.allowedHeaders("*");
//    }
//}
