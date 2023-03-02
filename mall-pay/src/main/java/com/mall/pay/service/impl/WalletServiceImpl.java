package com.mall.pay.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.pay.constant.ConsumerConstant;
import com.mall.pay.constant.PaymentConstant;
import com.mall.pay.dto.WalletDTO;
import com.mall.pay.entity.PayInfoEntity;
import com.mall.pay.entity.UserEntity;
import com.mall.pay.entity.WalletEntity;
import com.mall.pay.entity.WalletRecordsEntity;
import com.mall.pay.mapper.WalletMapper;
import com.mall.pay.service.PayInfoService;
import com.mall.pay.service.WalletRecordsService;
import com.mall.pay.service.WalletService;
import com.mall.pay.util.UserHolder;
import com.mall.pay.vo.WalletRecordsVo;
import com.mall.pay.vo.WalletVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NoPermissionException;
import java.util.List;

/**
 * @author ckstart
 * @create 2023-03-02 21:26
 */
@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, WalletEntity> implements WalletService {

    @Autowired
    private WalletRecordsService walletRecordsService;
    @Autowired
    private PayInfoService payInfoService;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void recharge(WalletDTO walletDTO) {
        WalletEntity walletEntity = new WalletEntity();
        LambdaUpdateWrapper<WalletEntity> updateWrapper = Wrappers.<WalletEntity>lambdaUpdate()
                .setSql("balance +=" + walletDTO.getAmount())
                .eq(WalletEntity::getUserId,walletDTO.getUserId());
        // update table_name set balance += amount where user_id = user_id and dr = 0
        baseMapper.update(walletEntity,updateWrapper);
        // 创建记录
        // 设置支付对象
        WalletRecordsEntity walletRecordsEntity = WalletRecordsEntity.builder()
                .amount(walletDTO.getAmount())
                .type(PaymentConstant.ALIPAY.getType())
                .consumerType(ConsumerConstant.RECHARGE.getType()).build();
        walletRecordsService.save(walletRecordsEntity);
    }

    @Override
    public void refund(Long payId) throws NoPermissionException {
        // 从ThreadLocal 获取用户id
        UserEntity userEntity = UserHolder.get();
        // 如果payId不是当前用户能操作的
        PayInfoEntity payInfoEntity = payInfoService.getById(userEntity.getId());
        // 对用户进行处理等
        PayInfoEntity payInfo = payInfoService.getById(payId);
        Assert.isTrue(payInfoEntity.getId().equals(payInfo.getUserId()),
                ()-> new NoPermissionException("你无权对别的用户进行操作,频繁操作进行封号处理"));
        Integer type = payInfo.getType();

        // 根据支付类型使用策略等设计模式获取对应的SDK实现
        // 调用sdk进行退款

    }

    @Override
    public WalletVo selectById() {
        UserEntity userEntity = UserHolder.get();
        return WalletVo.builder().balance(baseMapper.selectById(userEntity.getId()).getBalance()).build();
    }

    @Override
    public List<WalletRecordsVo> balanceDetail() {
        return walletRecordsService.getRecords();
    }
}
