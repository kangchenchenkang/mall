package com.mall.pay.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ckstart
 * @create 2023-03-02 21:40
 */
@Data
@Builder
public class WalletDTO {
    private Long userId;
    private BigDecimal amount;

}
