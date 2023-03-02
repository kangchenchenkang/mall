package com.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ckstart
 * @create 2023-03-02 21:55
 */
@TableName("wallet_records")
@Builder
@Data
public class WalletRecordsEntity {
    @TableId
    private Long id;
    private Long userId;
    private BigDecimal amount;
    /**
     * 充值类型{@link com.mall.pay.constant.PaymentConstant}
     * 支付宝充值，微信充值等
     */
    private Integer type;
    /**
     * 消费类型{@link com.mall.pay.constant.ConsumerConstant}
     */
    private Integer consumerType;
    private LocalDateTime createTime;
    private Long dr;

}
