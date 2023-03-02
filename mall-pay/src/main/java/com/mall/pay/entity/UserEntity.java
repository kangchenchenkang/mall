package com.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ckstart
 * @create 2023-03-02 19:56
 */
@Data
@TableName("user")
@Builder
public class UserEntity {
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String username;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Byte dr;
}
