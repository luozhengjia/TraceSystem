package com.ejunhai.trace.product.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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

    public List<Map<String, Object>> getProductAccessLogStatList(@Param("merchantId") Integer merchantId, @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);
}