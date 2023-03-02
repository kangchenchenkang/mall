package com.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.pay.entity.WalletRecordsEntity;
import com.mall.pay.vo.WalletRecordsVo;

import java.util.List;

/**
 * @author ckstart
 * @create 2023-03-02 22:08
 */
public interface WalletRecordsService extends IService<WalletRecordsEntity> {
    /**
     * 获取用户记录
     * @return
     */
    List<WalletRecordsVo> getRecords();
}
