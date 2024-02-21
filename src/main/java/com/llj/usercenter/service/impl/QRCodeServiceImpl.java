package com.llj.usercenter.service.impl;

import com.llj.usercenter.common.Result;
import com.llj.usercenter.service.QRCodeService;
import com.llj.usercenter.service.SyncDeadLockService;
import com.llj.usercenter.utils.deadlock.SyncDeadLock;
import com.llj.usercenter.utils.qrcode.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author 刘露杰
 * @Date 2024/2/21 14:40
 * @Description:
 */
@Slf4j
@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Override
    public Result<?> createQRImageByText() {
        String text = "宝宝有好好喝水么";// 二维码的内容
        String imgPath = "/Users/liulujie/Pictures/宝宝运动过程/WechatIMG24715.jpeg";// 二维码中的图片
        String destPath = "/Users/liulujie/Downloads/";// 二维码中存储路径
        try {
            QRCodeUtil.encode(text, imgPath, destPath, true);
        } catch (Exception e) {
            log.error("image create fail:", e);
        }
        return Result.ok();
    }
}
