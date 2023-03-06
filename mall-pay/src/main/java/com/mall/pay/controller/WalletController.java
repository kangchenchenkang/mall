package com.mall.pay.controller;

import com.mall.pay.dto.RefundDTO;
import com.mall.pay.dto.WalletDTO;
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
     * 查金额变动
     * @return
     */
    @GetMapping("details")
    public R detail(){
        // 省略分页
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
    public R refund(@RequestBody RefundDTO refundDTO) throws NoPermissionException {
        walletService.refund(refundDTO);
        return R.ok();
    }
    /**
     * 用户微信支付宝银行转账等支付方式,进行支付后调后,SDK回调接口
     * {@link com.mall.pay.listener.WalletListener} code in the WalletListener
     * @return
     */
    @PutMapping("recharge")
    public R recharge(@RequestBody  WalletDTO walletDTO){

        // 保存订单信息,设置状态为未支付,之后具体看回调接口WalletListener
        // 五分钟之后判断是否支付成功,还没有支付关闭订单
        walletService.recharge(walletDTO);
        return R.ok();
    }
}
