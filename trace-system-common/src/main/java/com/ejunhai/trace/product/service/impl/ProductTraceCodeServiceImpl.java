package com.ejunhai.trace.product.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.trace.product.dao.ProductTraceCodeMapper;
import com.ejunhai.trace.product.dto.ProductTraceCodeDto;
import com.ejunhai.trace.product.model.ProductTraceCode;
import com.ejunhai.trace.product.service.ProductTraceCodeService;

@Service("productTraceCodeService")
public class ProductTraceCodeServiceImpl implements ProductTraceCodeService {

    @Resource
    private ProductTraceCodeMapper productTraceCodeMapper;

    @Override
    public ProductTraceCode read(Integer id) {
        return productTraceCodeMapper.read(id);
    }

    @Override
    public ProductTraceCode getProductTraceCodeByCode(String code) {
        return productTraceCodeMapper.getProductTraceCodeByCode(code);
    }

    @Override
    public void batchInsert(List<ProductTraceCode> productTraceCodeList) {
        productTraceCodeMapper.batchInsert(productTraceCodeList);
    }

    @Override
    public void update(ProductTraceCode productTraceCode) {
        productTraceCode.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        productTraceCodeMapper.update(productTraceCode);
    }

    @Override
    public void delete(Integer id) {
        productTraceCodeMapper.delete(id);
    }

    @Override
    public Integer queryProductTraceCodeCount(ProductTraceCodeDto productTraceCodeDto) {
        return productTraceCodeMapper.queryProductTraceCodeCount(productTraceCodeDto);
    }

    @Override
    public List<ProductTraceCode> queryProductTraceCodeList(ProductTraceCodeDto productTraceCodeDto) {
        return productTraceCodeMapper.queryProductTraceCodeList(productTraceCodeDto);
    }

}
