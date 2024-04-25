package com.llj.usercenter.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyExample {

    interface Service {
        void execute();
    }

    static class ServiceImpl implements Service {
        @Override
        public void execute() {
            System.out.println("Service method executed");
        }
    }

    public static void main(String[] args) {
        Service service = new ServiceImpl();
        Service proxyService = (Service) Proxy.newProxyInstance(
                Service.class.getClassLoader(), // 类加载器
                new Class[]{Service.class}, // 代理接口
                new InvocationHandler() { // 调用处理器
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Before method execution");
                        Object result = method.invoke(service, args); // 调用原始方法
                        System.out.println("After method execution");
                        return result;
                    }
                }
        );
        proxyService.execute();
    }
}
