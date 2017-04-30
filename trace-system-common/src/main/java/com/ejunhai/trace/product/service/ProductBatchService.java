package com.ejunhai.trace.product.service;

import java.util.List;

import com.ejunhai.trace.product.dto.ProductBatchDto;
import com.ejunhai.trace.product.model.ProductBatch;

public interface ProductBatchService {

    /**
     * 根据Id获取ProductionBaseInfo
     * 
     * @param id
     * @return
     */
    public ProductBatch read(Integer id);

    public void save(ProductBatch productBatch);

    /**
     * 删除ProductBatch
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询ProductBatch数量
     * 
     * @param productBatchDto
     * @return
     */
    public Integer queryProductBatchCount(ProductBatchDto productBatchDto);

    /**
     * 查询ProductBatch列表
     * 
     * @param productBatchDto
     * @return
     */
    public List<ProductBatch> queryProductBatchList(ProductBatchDto productBatchDto);

}
