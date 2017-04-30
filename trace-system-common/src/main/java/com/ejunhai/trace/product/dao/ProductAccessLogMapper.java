package com.ejunhai.trace.product.dao;

import java.util.List;

import com.ejunhai.trace.product.dto.ProductAccessLogDto;
import com.ejunhai.trace.product.model.ProductAccessLog;

public interface ProductAccessLogMapper {

    /**
     * 新增productAccessLog
     * 
     * @param productAccessLog
     */
    public void insert(ProductAccessLog productAccessLog);

    /**
     * 查询ProductAccessLog数量
     * 
     * @param productBatchDto
     * @return
     */
    public Integer queryProductAccessLogCount(ProductAccessLogDto productBatchDto);

    /**
     * 查询ProductAccessLog列表
     * 
     * @param productBatchDto
     * @return
     */
    public List<ProductAccessLog> queryProductAccessLogList(ProductAccessLogDto productBatchDto);
}