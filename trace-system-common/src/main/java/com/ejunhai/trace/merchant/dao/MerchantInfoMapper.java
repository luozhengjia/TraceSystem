package com.ejunhai.trace.merchant.dao;

import java.util.List;

import com.ejunhai.trace.merchant.dto.MerchantDto;
import com.ejunhai.trace.merchant.model.MerchantInfo;

public interface MerchantInfoMapper {

    /**
     * 根据Id获取Merchant
     * 
     * @param id
     * @return
     */
    public MerchantInfo read(Integer id);

    /**
     * 新增Merchant
     * 
     * @param merchant
     */
    public void insert(MerchantInfo merchant);

    /**
     * 更新Merchant
     * 
     * @param merchant
     */
    public void update(MerchantInfo merchant);

    /**
     * 删除Merchant
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询Merchant数量
     * 
     * @param merchant
     * @return
     */
    public Integer queryMerchantCount(MerchantDto merchantDto);

    /**
     * 查询Merchant列表
     * 
     * @param merchant
     * @return
     */
    public List<MerchantInfo> queryMerchantList(MerchantDto merchantDto);

    /**
     * 根据ID获取商户列表
     * 
     * @param merchantIds
     * @return
     */
    public List<MerchantInfo> getMerchantListByIds(List<Integer> merchantIds);

    public List<MerchantInfo> getNoSmaMerchantList();
}