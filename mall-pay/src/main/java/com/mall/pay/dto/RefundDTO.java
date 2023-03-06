package com.mall.pay.dto;

import lombok.Data;

/**
 * @author ckstart
 * @create 2023-03-06 15:31
 */
@Data
public class RefundDTO {
    private String tradeNo;
    private Long orderId;
    /**
     * 全额退款还是部分退款
     */
    private Integer refundType;
}
