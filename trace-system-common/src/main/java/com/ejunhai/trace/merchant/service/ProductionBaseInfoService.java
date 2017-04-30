package com.ejunhai.trace.merchant.service;

import java.util.List;

import com.ejunhai.trace.merchant.dto.ProductionBaseInfoDto;
import com.ejunhai.trace.merchant.model.ProductionBaseInfo;

public interface ProductionBaseInfoService {

    /**
     * 根据Id获取ProductionBaseInfo
     * 
     * @param id
     * @return
     */
    public ProductionBaseInfo read(Integer id);

    public void save(ProductionBaseInfo productionBaseInfo);

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
    public List<ProductionBaseInfo> queryProductionBaseInfoList(ProductionBaseInfoDto productionBaseInfoDto);

    /**
     * 根据ID获取商户列表
     * 
     * @param baseIds
     * @return
     */
    public List<ProductionBaseInfo> getProductionBaseInfoListByIds(List<Integer> baseIds);
}
