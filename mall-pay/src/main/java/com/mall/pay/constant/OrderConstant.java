package com.mall.pay.constant;

import lombok.Getter;

/**
 * @author ckstart
 * @create 2023-03-02 20:19
 */
@Getter
public enum OrderConstant {

    /**
     * 支付成功
     */
    SUCCESS(1),
    /**
     * 支付失败
     */
    FAILED(2),
    /**
     * 未支付
     */
    UNPAID(3),
    /**
     * 退款
     */
    REFUND(4);

    private int type;

    OrderConstant(int type) {
        this.type = type;
    }
}
