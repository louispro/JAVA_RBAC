package com.louis.rabc.module.auth.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.auth.dao.BlackTokenDao;
import com.louis.rabc.module.auth.entity.BlackToken;
import com.louis.rabc.module.auth.service.BlackTokenService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * (BlackToken)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-18 00:21:06
 */
@Service("blackTokenService")
public class BlackTokenServiceImpl extends ServiceImpl<BlackTokenDao, BlackToken> implements BlackTokenService {


    /**
     * 每半小时清理存活时间超过2h的令牌
     */
    @Override
    @Scheduled(fixedDelay = 7200000)
    public void clearBlackToken() {
        List<BlackToken> blackTokenList = this.baseMapper.selectList(Wrappers.<BlackToken>lambdaQuery());
        blackTokenList.forEach(blackToken -> {
            Date createTime = blackToken.getCreateTime();
            Long hour = DateUtil.between(createTime, new Date(), DateUnit.HOUR);
            if (hour.compareTo(2L) == 1L) {
                this.baseMapper.deleteById(blackToken.getId());
            }
        });
    }

    public static void main(String[] args) {
        Date last = DateUtil.parse("2022-09-17 22:00:00");
        Date now = new Date();
        long hour = DateUtil.between(last, now, DateUnit.HOUR);
        System.out.println(hour);
    }
}

