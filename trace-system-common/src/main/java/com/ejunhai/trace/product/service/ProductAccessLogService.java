package com.ejunhai.trace.product.service;

import java.util.List;

import com.ejunhai.trace.product.dto.ProductAccessLogDto;
import com.ejunhai.trace.product.model.ProductAccessLog;

public interface ProductAccessLogService {

    /**
     * 新增productAccessLog
     * 
     * @param productAccessLog
     */
    public void insert(ProductAccessLog productAccessLog);

    /**
     * 查询ProductAccessLog数量
     * 
     * @param productAccessLogDto
     * @return
     */
    public Integer queryProductAccessLogCount(ProductAccessLogDto productAccessLogDto);

    /**
     * 查询ProductAccessLog列表
     * 
     * @param productAccessLogDto
     * @return
     */
    public List<ProductAccessLog> queryProductAccessLogList(ProductAccessLogDto productAccessLogDto);

}
