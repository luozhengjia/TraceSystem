package com.ejunhai.trace.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ejunhai.trace.product.dto.ProductBatchDto;
import com.ejunhai.trace.product.model.ProductBatch;

public interface ProductBatchMapper {
    /**
     * 根据Id获取ProductionBaseInfo
     * 
     * @param id
     * @return
     */
    public ProductBatch read(Integer id);

    /**
     * 新增ProductBatch
     * 
     * @param merchant
     */
    public void insert(ProductBatch productBatch);

    /**
     * 批量插入
     * 
     * @param productBatchList
     */
    public void batchInsert(List<ProductBatch> productBatchList);

    /**
     * 更新ProductBatch
     * 
     * @param merchant
     */
    public void update(ProductBatch productBatch);

    public void updateHaveIssueNum(@Param("id") Integer id, @Param("num") Integer num);

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