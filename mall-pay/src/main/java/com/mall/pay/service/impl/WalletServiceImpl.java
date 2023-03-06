package com.mall.pay.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.pay.constant.ConsumerConstant;
import com.mall.pay.constant.OrderConstant;
import com.mall.pay.constant.PaymentConstant;
import com.mall.pay.dto.RefundDTO;
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
        PayInfoEntity payInfoEntity = PayInfoEntity.builder()
                .totalAmount(walletDTO.getAmount())
                // 设置状态为未支付
                .status(OrderConstant.UNPAID.getType())
                //省略 ...
                //.tradeNo() ...
                .build();
        payInfoService.save(payInfoEntity);
        // 创建记录
        // 设置支付对象
        WalletRecordsEntity walletRecordsEntity = WalletRecordsEntity.builder()
                .amount(walletDTO.getAmount())
                .payType(PaymentConstant.ALIPAY.getType())
                .consumerType(ConsumerConstant.RECHARGE.getType()).build();
        walletRecordsService.save(walletRecordsEntity);
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void refund(RefundDTO refundDTO) throws NoPermissionException {
        // 从ThreadLocal 获取用户id
        UserEntity userEntity = UserHolder.get();
        // 判断tradeNo对应的用是不是当前用户的
        PayInfoEntity payInfoEntity = payInfoService.getById(userEntity.getId());
        Assert.notNull(payInfoEntity,"不存在的流水号");
        // 对用户进行处理等
        PayInfoEntity payInfo = payInfoService.getByTradeNo(refundDTO.getTradeNo());
        Assert.isTrue(payInfoEntity.getId().equals(payInfo.getUserId()),
                ()-> new NoPermissionException("你无权对别的用户进行操作,频繁操作进行封号处理"));
        // TODO 部分退款记录订单,全额退款记录,省略
        Integer status = payInfo.getStatus();
        // 只有支付成功的状态才能退款
        Assert.isTrue(OrderConstant.SUCCESS.equals(status),"支付失败不能退款");

        Assert.notNull(payInfoEntity,"不存在的流水号");
        // 修改订单状态
        payInfoService.update(Wrappers.<PayInfoEntity>lambdaUpdate()
                .eq(PayInfoEntity::getId,payInfoEntity.getId())
                .set(PayInfoEntity::getStatus,OrderConstant.REFUND.getType()));
        // 记录退款
        WalletRecordsEntity walletRecordsEntity = WalletRecordsEntity.builder()
                // 记录是部分退款还是全额退款
                .consumerType(ConsumerConstant.PARTIAL_REFUND.getType())
                .payType(PaymentConstant.ALIPAY.getType())
                // TODO 退款金额根据查询得出 省略
                //.amount()
                //.orderSn()
                .build();
        walletRecordsService.save(walletRecordsEntity);
        // 根据支付类型使用策略等设计模式获取对应的SDK适配器模式的实现
        Integer type = payInfoEntity.getType();
        // 调用SDK进行退款
    }

    @Override
    public WalletVo selectById() {
        UserEntity userEntity = UserHolder.get();
        return WalletVo.builder().balance(baseMapper.selectById(userEntity.getId()).getBalance()).build();
    }

    @Override
    public List<WalletRecordsVo> balanceDetail() {
        // 省略分页
        return walletRecordsService.getRecords();
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)

    @Override
    public String callbackRecharge(WalletDTO walletDTO) {
        // TODO 查询充值对象记录
        // TODO where条件判断状态是否充值成功,幂等性判断(断网网络分区没有收到回调等情况)
        // 设置充值完成状态,等一系列数据,update修改记录
        // 修改金额
        WalletEntity walletEntity = new WalletEntity();
        LambdaUpdateWrapper<WalletEntity> updateWrapper = Wrappers.<WalletEntity>lambdaUpdate()
                .setSql("balance +=" + walletDTO.getAmount())
                .eq(WalletEntity::getUserId,walletDTO.getUserId());
        // update table_name set balance += amount where user_id = user_id and dr = 0
        baseMapper.update(walletEntity,updateWrapper);
        // 返回给微信支付宝收到通知
        return "success";
    }
}
