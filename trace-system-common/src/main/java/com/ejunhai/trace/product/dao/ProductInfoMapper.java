package com.ejunhai.trace.product.dao;

import java.util.List;

import com.ejunhai.trace.product.dto.ProductInfoDto;
import com.ejunhai.trace.product.model.ProductInfo;

public interface ProductInfoMapper {
    /**
     * 根据Id获取ProductInfo
     * 
     * @param id
     * @return
     */
    public ProductInfo read(Integer id);

    /**
     * 新增ProductInfo
     * 
     * @param merchant
     */
    public void insert(ProductInfo productInfo);

    /**
     * 更新ProductInfo
     * 
     * @param merchant
     */
    public void update(ProductInfo productInfo);

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

}