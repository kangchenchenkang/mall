package com.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ckstart
 * @create 2023-03-02 21:23
 */
@Data
@TableName("wallet")
public class WalletEntity {

    /**
     * 用户id,平时用不到钱包拆分用户表
     */
    @TableId
    private Long userId;
    /**
     * 余额
     */
    private BigDecimal balance;

    @TableField( fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField( fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    private Byte dr;
}
