package com.ejunhai.trace.product.service;

import java.util.List;

import com.ejunhai.trace.product.dto.ProductInfoDto;
import com.ejunhai.trace.product.model.ProductInfo;

public interface ProductInfoService {

    /**
     * 根据Id获取ProductInfo
     * 
     * @param id
     * @return
     */
    public ProductInfo read(Integer id);

    public void save(ProductInfo productInfo);

    /**
     * 删除ProductInfo
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询ProductInfo数量
     * 
     * @param merchant
     * @return
     */
    public Integer queryProductInfoCount(ProductInfoDto productInfoDto);

    /**
     * 查询ProductInfo列表
     * 
     * @param merchant
     * @return
     */
    public List<ProductInfo> queryProductInfoList(ProductInfoDto productInfoDto);

    /**
     * 根据ID获取产品列表
     * 
     * @param productIds
     * @return
     */
    public List<ProductInfo> getProductInfoListByIds(List<Integer> productIds);

    /**
     * 根据merchantId获取产品列表
     * 
     * @param merchantId
     * @return
     */
    public List<ProductInfo> getProductInfoListByMerchantId(Integer merchantId);

}
