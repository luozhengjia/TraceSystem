package com.ejunhai.trace.merchant.service;

import java.util.List;

import com.ejunhai.trace.merchant.dto.MerchantDto;
import com.ejunhai.trace.merchant.model.MerchantInfo;

/**
 * 
 * Merchant Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:42:31
 * 
 */
public interface MerchantService {

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

    /**
     * 获取没有商户户主的商户列表
     * 
     * @return
     */
    public List<MerchantInfo> getNoSmaMerchantList();
}
