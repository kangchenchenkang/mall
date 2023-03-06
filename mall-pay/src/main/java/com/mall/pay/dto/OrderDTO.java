package com.mall.pay.dto;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author ckstart
 * @create 2023-03-02 21:05
 */
public class OrderDTO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 支付平台订单id
     */
    private String payId;

    /**
     * 支付类型{@link com.mall.pay.constant.PaymentConstant}
     */
    private Integer payType;

    public class OrderItemDTO{

    }
}
