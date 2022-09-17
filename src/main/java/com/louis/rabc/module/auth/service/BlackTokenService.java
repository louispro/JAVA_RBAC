package com.louis.rabc.module.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.rabc.module.auth.entity.BlackToken;

/**
 * (BlackToken)表服务接口
 *
 * @author 赖小燚
 * @since 2022-09-18 00:21:06
 */
public interface BlackTokenService extends IService<BlackToken> {

    /**
     * 清理黑名单令牌
     */
    void clearBlackToken();


}

