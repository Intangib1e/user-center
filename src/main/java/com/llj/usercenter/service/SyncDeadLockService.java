package com.llj.usercenter.service;

import com.llj.usercenter.common.Result;

/**
 * @Author 刘露杰
 * @Date 2024/2/21 14:40
 * @Description:
 */
public interface SyncDeadLockService {
    Result<?> triggerDeadLock();

}
