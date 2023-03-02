package com.mall.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.pay.entity.UserEntity;
import com.mall.pay.entity.WalletRecordsEntity;
import com.mall.pay.mapper.WalletRecordsMapper;
import com.mall.pay.service.WalletRecordsService;
import com.mall.pay.util.UserHolder;
import com.mall.pay.vo.WalletRecordsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ckstart
 * @create 2023-03-02 22:09
 */
@Service
public class WalletRecordsServiceImpl extends ServiceImpl<WalletRecordsMapper, WalletRecordsEntity> implements WalletRecordsService {
    @Override
    public List<WalletRecordsVo> getRecords() {
        UserEntity userEntity = UserHolder.get();
        LambdaQueryWrapper<WalletRecordsEntity> lambdaQueryWrapper = Wrappers.<WalletRecordsEntity>lambdaQuery()
                .eq(WalletRecordsEntity::getUserId, userEntity.getId());
        List<WalletRecordsEntity> walletRecordsEntities = baseMapper.selectList(lambdaQueryWrapper);
        if (CollectionUtils.isEmpty(walletRecordsEntities)) {
            return Collections.emptyList();
        }
        return walletRecordsEntities.stream().map((item)->{
            WalletRecordsVo walletRecordsVo = new WalletRecordsVo();
            BeanUtils.copyProperties(item,walletRecordsVo);
            return walletRecordsVo;
        }).collect(Collectors.toList());
    }
}
