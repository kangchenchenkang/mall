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

        // 进行非对称加密验签
        // 充值成功进行修改钱包
        // 解密获取对象,省略
        WalletDTO walletDTO = WalletDTO.builder().userId(IdWorker.getId()).amount(BigDecimal.valueOf(10)).build();
        walletService.recharge(walletDTO);
        return "success";
    }

    /**
     * 回调退款成功
     * @return
     */
    @PostMapping("recharge")
    public String refund(){

        // 进行非对称加密验签
        // 退款成功进行修改钱包
        // 解密获取对象,省略

        // 变更状态跟这次支付相关的信息
        return "success";
    }
}
