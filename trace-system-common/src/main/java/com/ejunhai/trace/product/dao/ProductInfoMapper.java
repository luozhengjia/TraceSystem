package com.ejunhai.trace.product.dao;

import java.util.List;

import com.ejunhai.trace.merchant.dto.ProductionBaseInfoDto;
import com.ejunhai.trace.merchant.model.ProductionBaseInfo;

public interface ProductInfoMapper {
    /**
     * 根据Id获取ProductionBaseInfo
     * 
     * @param id
     * @return
     */
    public ProductionBaseInfo read(Integer id);

    /**
     * 新增ProductionBaseInfo
     * 
     * @param merchant
     */
    public void insert(ProductionBaseInfo productionBaseInfo);

    /**
     * 更新ProductionBaseInfo
     * 
     * @param merchant
     */
    public void update(ProductionBaseInfo productionBaseInfo);

    /**
     * 删除ProductionBaseInfo
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询ProductionBaseInfo数量
     * 
     * @param merchant
     * @return
     */
    public Integer queryProductionBaseInfoCount(ProductionBaseInfoDto productionBaseInfoDto);

    /**
     * 查询ProductionBaseInfo列表
     * 
     * @param merchant
     * @return
     */
    public List<ProductionBaseInfo> queryProductionBaseInfoList(ProductionBaseInfoDto troductionBaseInfoDto);

}