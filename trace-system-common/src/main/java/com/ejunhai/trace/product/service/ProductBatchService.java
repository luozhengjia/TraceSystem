package com.ejunhai.trace.product.service;

import java.util.List;

import com.ejunhai.trace.product.dto.ProductBatchDto;
import com.ejunhai.trace.product.model.ProductBatch;

/**
 * @author pacel
 * 
 */
public interface ProductBatchService {

    /**
     * 根据Id获取ProductionBaseInfo
     * 
     * @param id
     * @return
     */
    public ProductBatch read(Integer id);

    /**
     * 根据批次号获取批次
     * 
     * @param batchNo
     * @return
     */
    public ProductBatch getProductBatchByBatchNo(String batchNo);

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

    /**
     * 生存溯源码
     * 
     * @param id
     */
    public void generateTraceCodes(Integer id);

}
