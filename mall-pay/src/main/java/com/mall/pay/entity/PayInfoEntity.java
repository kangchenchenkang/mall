package com.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ckstart
 * @create 2023-03-02 20:59
 */
@TableName("pay_info")
@Data
@Builder
public class PayInfoEntity {
    private Long id;

    /**
     * {@link com.mall.pay.constant.PaymentConstant}
     */
    private Integer type;
    /**
     * {@link com.mall.pay.constant.OrderConstant}
     */
    private Integer status;
    private Long orderId;
    private Long userId;
    private Long orderSn;
    private String tradeNo;
    private BigDecimal totalAmount;
}
