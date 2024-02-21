package com.llj.usercenter.service.impl;

import com.llj.usercenter.common.Result;
import com.llj.usercenter.service.SyncDeadLockService;
import com.llj.usercenter.utils.deadlock.SyncDeadLock;
import org.springframework.stereotype.Service;

/**
 * @Author 刘露杰
 * @Date 2024/2/21 14:40
 * @Description:
 */
@Service
public class SyncDeadLockServiceImpl implements SyncDeadLockService {
    @Override
    public Result<?> triggerDeadLock() {
        SyncDeadLock.deadLock();
        return Result.ok();
    }
}
