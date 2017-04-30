package com.ejunhai.trace.merchant.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.trace.merchant.dao.ProductionBaseInfoMapper;
import com.ejunhai.trace.merchant.dto.ProductionBaseInfoDto;
import com.ejunhai.trace.merchant.model.ProductionBaseInfo;
import com.ejunhai.trace.merchant.service.ProductionBaseInfoService;

@Service("productionBaseInfoService")
public class ProductionBaseInfoServiceImpl implements ProductionBaseInfoService {

    @Resource
    private ProductionBaseInfoMapper productionBaseInfoMapper;

    @Override
    public ProductionBaseInfo read(Integer id) {
        return productionBaseInfoMapper.read(id);
    }

    @Override
    public void save(ProductionBaseInfo productionBaseInfo) {
        // TODO Auto-generated method stub
        if (productionBaseInfo.getId() != null) {
            productionBaseInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            productionBaseInfoMapper.update(productionBaseInfo);
        } else {
            productionBaseInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            productionBaseInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            productionBaseInfoMapper.insert(productionBaseInfo);
        }
    }

    @Override
    public void delete(Integer id) {
        productionBaseInfoMapper.delete(id);
    }

    @Override
    public Integer queryProductionBaseInfoCount(ProductionBaseInfoDto productionBaseInfoDto) {
        return productionBaseInfoMapper.queryProductionBaseInfoCount(productionBaseInfoDto);
    }

    @Override
    public List<ProductionBaseInfo> queryProductionBaseInfoList(ProductionBaseInfoDto productionBaseInfoDto) {
        return productionBaseInfoMapper.queryProductionBaseInfoList(productionBaseInfoDto);
    }

}
