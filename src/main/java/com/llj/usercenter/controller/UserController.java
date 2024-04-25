package com.llj.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llj.usercenter.common.Result;
import com.llj.usercenter.common.ResultCodeEnum;
import com.llj.usercenter.exception.BusinessException;
import com.llj.usercenter.model.domain.User;
import com.llj.usercenter.model.request.UserLoginRequest;
import com.llj.usercenter.model.request.UserRegisterRequest;
import com.llj.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

import static com.llj.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.llj.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @Author 刘露杰
 * @Date 2023/5/28 13:34
 * @Description: 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<?> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ResultCodeEnum.ARGUMENT_VALID_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            throw new BusinessException(ResultCodeEnum.ARGUMENT_VALID_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return Result.ok(result);
    }

    @PostMapping("/login")
    public Result<?> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return Result.ok(user);
    }

    @PostMapping("/logout")
    public Result<?> userLogout(HttpServletRequest httpServletRequest){
        if (httpServletRequest == null){
            return null;
        }
        int result = userService.userLogout(httpServletRequest);
        return Result.ok(result);
    }

    @GetMapping("/current")
    public Result<?> getCurrent(HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) attribute;
        if (currentUser == null){
            return null;
        }
        Long id = currentUser.getId();
        //TODO 校验用户是否合法
        User user = userService.getById(id);
        User safetyUser = userService.getSafetyUser(user);
        return Result.ok(safetyUser);
    }

    @GetMapping("/search")
    public Result<?> searchUsers(String username, HttpServletRequest request){
        // 鉴权-仅管理员可以
        if (!isAdmin(request)){
            throw new BusinessException(ResultCodeEnum.PERMISSION);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper).stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return Result.ok(userList);
    }

    @PostMapping("/delete")
    public Result<?> deleteUser(@RequestBody long id, HttpServletRequest request){
        // 鉴权-仅管理员可以
        if (!isAdmin(request)){
            return  Result.fail();
        }
        if (id <= 0){
            return Result.fail();
        }
        boolean remove = userService.removeById(id);
        return Result.ok(remove);
    }


    @GetMapping("/getImage")
    public BufferedImage getImage(@RequestParam String content, @RequestParam int weight, @RequestParam int height){
        return userService.getQrCode(content, weight, height);
    }

    /**
     * 是否为管理员
     * @param request 请求
     * @return 是否为管理员 true or false
     */
    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
