package com.mall.pay.listener;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mall.pay.dto.WalletDTO;
import com.mall.pay.entity.WalletEntity;
import com.mall.pay.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author ckstart
 * @create 2023-03-02 20:25
 */
@RestController
@RequestMapping("listener")
public class WalletListener {
    @Autowired
    private WalletService walletService;
    /**
     * 回调充值成功
     * @return
     */
    @PostMapping("recharge")
    public String recharge(){
        // 支付宝微信等会进行最大努力通知,进行幂等性判断,getByTradeNo查询是否有充值记录
        // 进行非对称加密验签
        // 充值成功进行修改钱包
        // 根据订单编号查询订单,得到对应订单的钱包信息,省略...
        WalletDTO walletDTO = WalletDTO.builder().build();
        // 返回处理微信支付通知成功
        return  walletService.callbackRecharge(walletDTO);
    }

    /**
     * 回调退款成功
     * @return
     */
    @PostMapping("recharge")
    public String refund(){
        // 支付宝微信等会进行最大努力通知,进行幂等性判断查询是否有退款记录
        // 进行非对称加密验签
        // 退款成功修改钱包

        // 返回处理微信支付通知成功
        return  walletService.callbackRefund("tradeNo等信息");
    }
}
