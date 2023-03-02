package com.mall.pay.constant;

import lombok.Getter;

/**
 * @author ckstart
 * @create 2023-03-02 20:19
 */
@Getter
public enum PaymentConstant {

    /**
     * 支付宝
     */
    ALIPAY(1),
    /**
     * 微信支付
     */
    WECHAT(2),
    /**
     * 其他
     */
    OTHER(3);

    private int type;

    PaymentConstant(int type) {
        this.type = type;
    }
}
