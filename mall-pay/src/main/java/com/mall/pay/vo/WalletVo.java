package com.mall.pay.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ckstart
 * @create 2023-03-02 22:34
 */
@Data
@Builder
public class WalletVo {
    private BigDecimal balance;
}
