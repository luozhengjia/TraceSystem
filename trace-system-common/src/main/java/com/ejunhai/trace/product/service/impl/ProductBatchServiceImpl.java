package com.ejunhai.trace.product.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.trace.product.dao.ProductBatchMapper;
import com.ejunhai.trace.product.dto.ProductBatchDto;
import com.ejunhai.trace.product.model.ProductBatch;
import com.ejunhai.trace.product.service.ProductBatchService;

@Service("productBatchService")
public class ProductBatchServiceImpl implements ProductBatchService {

    @Resource
    private ProductBatchMapper productBatchMapper;

    @Override
    public ProductBatch read(Integer id) {
        return productBatchMapper.read(id);
    }

    @Override
    public void save(ProductBatch productBatch) {
        if (productBatch.getId() != null) {
            productBatch.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            productBatchMapper.update(productBatch);
        } else {
            productBatch.setCreateTime(new Timestamp(System.currentTimeMillis()));
            productBatch.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            productBatchMapper.insert(productBatch);
        }
    }

    @Override
    public void delete(Integer id) {
        productBatchMapper.delete(id);
    }

    @Override
    public Integer queryProductBatchCount(ProductBatchDto productBatchDto) {
        return productBatchMapper.queryProductBatchCount(productBatchDto);
    }

    @Override
    public List<ProductBatch> queryProductBatchList(ProductBatchDto productBatchDto) {
        return productBatchMapper.queryProductBatchList(productBatchDto);
    }

}
