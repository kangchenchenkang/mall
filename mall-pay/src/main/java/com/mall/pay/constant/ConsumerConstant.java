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
     * 部分退款
     */
    PARTIAL_REFUND(3),
    /**
     * 支付
     */
    PAY(4),
    /**
     * 其他
     */
    OTHER(5);

    private int type;

    ConsumerConstant(int type) {
        this.type = type;
    }
}
