package com.llj.usercenter.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.llj.usercenter.mapper.UserMapper;
import com.llj.usercenter.model.domain.User;
import com.llj.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.llj.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @author liulujie
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-05-15 21:21:34
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 加密用盐
     */
    private static final String SALT = "arc";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1-校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }
        //账户不能包涵特殊字符
        String validPattern = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        //密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        //账户不能重复
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("userAccount", userAccount);
        int count = this.count(wrapper);
        if (count > 0) {
            return -1;
        }

        //2-加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //3-插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean result = this.save(user);
        if (!result) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest) {
        //1-校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            //TODO 修改为自定义异常
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (userPassword.length() < 8) {
            return null;
        }
        //账户不能包涵特殊字符
        String validPattern = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        //2-加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //查询用户是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("userAccount", userAccount);
        wrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(wrapper);
        //用户不存在
        if (user == null){
            log.info("user login failed,userAccount cannot match userPassword");
            return null;
        }
        //3-用户脱敏
        User safetyUser = getSafetyUser(user);
        //4-记录用户登录态
        httpServletRequest.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);
        //返回用户信息
        return safetyUser;
    }

    @Override
    public int userLogout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }


    /**
     * 用户数据脱敏
     * @param originUser 用户信息
     * @return 脱敏后的用户信息
     */
    @Override
    public User getSafetyUser(User originUser){
        if (originUser == null){
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setUserPassword(null);
        safetyUser.setGender(originUser.getGender());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setIsDeleted(originUser.getIsDeleted());
        return safetyUser;
    }


    /**
     * 根据内容 获取二维码
     * @param content 内容
     * @return
     */
    @Override
    public BufferedImage getQrCode(String content, int width, int height) {

        //二维码设置
        QrConfig config = getConfig(width, height);
        //生成指定url对应的二维码到文件，宽和高都是300像素
        BufferedImage bufferedImage = QrCodeUtil.generate(
                content,
                config);
        // 插入图片
        return bufferedImage;
    }

    /**
     * 二维码基础设置
     * @param width 宽 300
     * @param height 长 300
     * @return
     */
    public static QrConfig getConfig(int width, int height){
        //300,300
        QrConfig config = new QrConfig(width, height);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(2);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.BLACK);
        // 设置背景色（灰色）
        config.setBackColor(Color.WHITE);
        // 高纠错级别 :像素块小，允许遮挡一定范围，但是像素块更密集
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        return config;
    }

}




