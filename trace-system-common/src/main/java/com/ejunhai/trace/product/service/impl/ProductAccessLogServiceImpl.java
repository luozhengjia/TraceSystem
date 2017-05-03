package com.ejunhai.trace.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import me.zzp.district.DistrictHelper;

import org.springframework.stereotype.Service;

import com.ejunhai.trace.product.dao.ProductAccessLogMapper;
import com.ejunhai.trace.product.dto.ProductAccessLogDto;
import com.ejunhai.trace.product.model.ProductAccessLog;
import com.ejunhai.trace.product.service.ProductAccessLogService;

@Service("productAccessLogService")
public class ProductAccessLogServiceImpl implements ProductAccessLogService {

    @Resource
    private ProductAccessLogMapper productAccessLogMapper;

    @Override
    public void insert(ProductAccessLog productAccessLog) {
        if (productAccessLog.getSourceIp() != null) {
            productAccessLog.setLocation(DistrictHelper.ofIp(productAccessLog.getSourceIp()));
        }
        productAccessLogMapper.insert(productAccessLog);
    }

    @Override
    public Integer queryProductAccessLogCount(ProductAccessLogDto productAccessLogDto) {
        return productAccessLogMapper.queryProductAccessLogCount(productAccessLogDto);
    }

    @Override
    public List<ProductAccessLog> queryProductAccessLogList(ProductAccessLogDto productAccessLogDto) {
        return productAccessLogMapper.queryProductAccessLogList(productAccessLogDto);
    }

}
