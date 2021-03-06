package com.ejunhai.trace.merchant.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.trace.merchant.dao.MerchantInfoMapper;
import com.ejunhai.trace.merchant.dto.MerchantDto;
import com.ejunhai.trace.merchant.model.MerchantInfo;
import com.ejunhai.trace.merchant.service.MerchantService;

/**
 * Merchant Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:42:31
 * 
 */
@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantInfoMapper merchantMapper;

    @Override
    public MerchantInfo read(Integer id) {
        return merchantMapper.read(id);
    }

    @Override
    public void insert(MerchantInfo merchant) {
        merchant.setCreateTime(new Timestamp(System.currentTimeMillis()));
        merchant.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        merchantMapper.insert(merchant);
    }

    @Override
    public void update(MerchantInfo merchant) {
        merchant.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        merchantMapper.update(merchant);
    }

    @Override
    public void delete(Integer id) {
        merchantMapper.delete(id);
    }

    @Override
    public Integer queryMerchantCount(MerchantDto merchantDto) {
        return merchantMapper.queryMerchantCount(merchantDto);
    }

    @Override
    public List<MerchantInfo> queryMerchantList(MerchantDto merchantDto) {
        return merchantMapper.queryMerchantList(merchantDto);
    }

    @Override
    public List<MerchantInfo> getMerchantListByIds(List<Integer> merchantIds) {
        if (merchantIds == null || merchantIds.isEmpty()) {
            return new ArrayList<MerchantInfo>();
        }
        return merchantMapper.getMerchantListByIds(merchantIds);
    }

    @Override
    public List<MerchantInfo> getNoSmaMerchantList() {
        return merchantMapper.getNoSmaMerchantList();
    }

}
