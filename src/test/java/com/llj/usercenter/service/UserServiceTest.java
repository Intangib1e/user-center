package com.llj.usercenter.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.llj.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void addUserTest(){
        User user = new User();
        user.setUsername("testLLJ");
        user.setUserAccount("123");
        user.setUserPassword("123456");
        user.setGender(0);
        user.setAvatarUrl("https://image.so.com/z?a=viewPage&ancestor=list&ch=beauty&t1=625&clw=283#id=ea34bcc774d5fad3f81670baabad2ff9&grpid=aa5d7492080c5c0ad013fecb7e406b9b&currsn=0");
        user.setEmail("lljsln@163.com");
        user.setPhone("15194100259");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "arc0316";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        System.out.println(result);
    }


    @Test
    void testdigui(){
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int target = 0;
        int result = binarySearch(arr, target, 0, arr.length - 1);
        if (result == -1) {
            System.out.println("Target not found");
        } else {
            System.out.println("Target found at index " + result);
        }
    }

    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                return binarySearch(arr, target, mid + 1, right);
            } else {
                return binarySearch(arr, target, left, mid - 1);
            }
        }
        return -1; // target not found
    }

}
