package com.llj.usercenter.service;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyExample {

//    static final class Service {
//        public void execute() {
//            System.out.println("Service method executed");
//        }
//    }

    static class Service {
        public void execute() {
            System.out.println("Service method executed");
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("Before method execution");
                Object result = proxy.invokeSuper(obj, args); // 调用原始方法
                System.out.println("After method execution");
                return result;
            }
        });

        Service proxyService = (Service) enhancer.create();
        proxyService.execute();
    }
}
