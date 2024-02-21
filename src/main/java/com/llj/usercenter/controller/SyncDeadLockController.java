package com.llj.usercenter.controller;

import com.llj.usercenter.common.Result;
import com.llj.usercenter.service.SyncDeadLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 刘露杰
 * @Date 2024/2/21 14:38
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/admin/sysDeadLock")
public class SyncDeadLockController {

    @Autowired
    private SyncDeadLockService syncDeadLockService;

    @GetMapping("/triggerDeadLock")
    public Result<?> triggerDeadLock(){
        return syncDeadLockService.triggerDeadLock();
    }

}
