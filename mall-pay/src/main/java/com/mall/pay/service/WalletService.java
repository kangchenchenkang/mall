package com.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.pay.dto.RefundDTO;
import com.mall.pay.dto.WalletDTO;
import com.mall.pay.entity.WalletEntity;
import com.mall.pay.vo.WalletRecordsVo;
import com.mall.pay.vo.WalletVo;

import javax.naming.NoPermissionException;
import java.util.List;

/**
 * @author ckstart
 * @create 2023-03-02 21:26
 */
public interface WalletService extends IService<WalletEntity> {
    /**
     * 充值
     * @param walletDTO 充值信息
     */
    void recharge(WalletDTO walletDTO);

    /**
     * 退
     * @param refundDTO
     */
    void refund(RefundDTO refundDTO) throws NoPermissionException;

    /**
     * 查询余额
     * @return
     */
    WalletVo selectById();


    /**
     * 金额明细
     * @return
     */
    List<WalletRecordsVo> balanceDetail();


    /**
     *
     * @param walletDTO
     * @return
     */
    String callbackRecharge(WalletDTO walletDTO);
}
