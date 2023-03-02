package com.mall.pay.controller;

import com.mall.pay.service.WalletService;
import com.mall.pay.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;

/**
 * @author ckstart
 * @create 2023-03-02 21:11
 */
@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;


    /**
     * 查询余额
     * @return
     */
    @GetMapping("details")
    public R detail(){
        return R.ok(walletService.balanceDetail());
    }

    /**
     * 查询余额
     * @return
     */
    @GetMapping("balance")
    public R balance(){
        return R.ok(walletService.selectById());
    }
    /**
     * 退款
     * @return
     */
    @PutMapping("refund")
    public R refund(@RequestBody Long payId) throws NoPermissionException {
        walletService.refund(payId);
        return R.ok();
    }
    /**
     * 用户微信支付宝银行转账等支付方式,进行支付后调后,SDK回调接口
     * {@link com.mall.pay.listener.WalletListener} code in the WalletListener
     * @return
     */
    @PutMapping("recharge")
    public R recharge(){
        return R.ok();
    }
}
