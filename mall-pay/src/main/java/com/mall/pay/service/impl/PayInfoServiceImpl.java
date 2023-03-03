package com.mall.pay.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.pay.entity.PayInfoEntity;
import com.mall.pay.mapper.PayInfoMapper;
import com.mall.pay.service.PayInfoService;
import org.springframework.stereotype.Service;

/**
 * @author ckstart
 * @create 2023-03-02 22:21
 */
@Service
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfoEntity> implements PayInfoService {
    @Override
    public PayInfoEntity getByTradeNo(String tradeNo) {

        return baseMapper.selectOne(Wrappers.<PayInfoEntity>lambdaQuery().eq(PayInfoEntity::getTradeNo,tradeNo));
    }
}
