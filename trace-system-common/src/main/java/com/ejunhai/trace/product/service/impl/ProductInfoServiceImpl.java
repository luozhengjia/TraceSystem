package com.ejunhai.trace.product.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.trace.product.dao.ProductInfoMapper;
import com.ejunhai.trace.product.dto.ProductInfoDto;
import com.ejunhai.trace.product.model.ProductInfo;
import com.ejunhai.trace.product.service.ProductInfoService;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductInfoMapper productInfoMapper;

    @Override
    public ProductInfo read(Integer id) {
        return productInfoMapper.read(id);
    }

    @Override
    public void save(ProductInfo productInfo) {
        if (productInfo.getId() != null) {
            productInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            productInfoMapper.update(productInfo);
        } else {
            productInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            productInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            productInfoMapper.insert(productInfo);
        }
    }

    @Override
    public void delete(Integer id) {
        productInfoMapper.delete(id);
    }

    @Override
    public Integer queryProductInfoCount(ProductInfoDto productInfoDto) {
        return productInfoMapper.queryProductInfoCount(productInfoDto);
    }

    @Override
    public List<ProductInfo> queryProductInfoList(ProductInfoDto productInfoDto) {
        return productInfoMapper.queryProductInfoList(productInfoDto);
    }

}
