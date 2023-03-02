package com.mall.pay.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ckstart
 * @create 2023-03-02 20:04
 */
@Data
public class OrderItemEntity {
    private Long id;
    private BigDecimal total;
    private Integer status;
    private Byte dr;
}
