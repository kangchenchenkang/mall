package com.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ckstart
 * @create 2023-03-02 19:58
 */
@Data
@TableName("payment")
@Builder
public class OrderEntity {
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 支付平台订单id
     */
    private String payId;

    /**
     * 支付类型{@link com.mall.pay.constant.PaymentConstant}
     */
    private Integer payType;

    private Integer status;

    private Byte dr;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    /**
     * 创建者
     */
    private Long creator;
    /**
     * 最近一次修改者
     */
    private Long modifier;
}
