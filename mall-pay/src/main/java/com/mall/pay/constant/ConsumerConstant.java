package com.mall.pay.constant;

import lombok.Getter;

/**
 * @author ckstart
 * @create 2023-03-02 20:19
 */
@Getter
public enum ConsumerConstant {

    /**
     * 充值
     */
    RECHARGE(1),
    /**
     * 退款
     */
    REFUND(2),
    /**
     * 支付
     */
    PAY(3),
    /**
     * 其他
     */
    OTHER(4);

    private int type;

    ConsumerConstant(int type) {
        this.type = type;
    }
}
