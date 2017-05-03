package com.ejunhai.trace.product.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.trace.product.dao.ProductBatchMapper;
import com.ejunhai.trace.product.dto.ProductBatchDto;
import com.ejunhai.trace.product.enums.TracecodeState;
import com.ejunhai.trace.product.model.ProductBatch;
import com.ejunhai.trace.product.model.ProductTraceCode;
import com.ejunhai.trace.product.service.ProductBatchService;
import com.ejunhai.trace.product.service.ProductTraceCodeService;

@Service("productBatchService")
public class ProductBatchServiceImpl implements ProductBatchService {

    @Resource
    private ProductBatchMapper productBatchMapper;

    @Resource
    private ProductTraceCodeService productTraceCodeService;

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

    @Override
    public synchronized void generateTraceCodes(Integer id) {
        ProductBatch productBatch = productBatchMapper.read(id);
        Integer maxGenNum = productBatch.getIssueAmount() - productBatch.getHasIssueNum();
        int onceLimitGenNum = 100000;
        Integer availableGenNum = maxGenNum > onceLimitGenNum ? onceLimitGenNum : maxGenNum;

        int onceGenNum = 1024;
        int page = (availableGenNum + onceGenNum - 1) / onceGenNum;
        for (int i = 0; i < page; i++) {
            int batchNum = i == page - 1 ? availableGenNum - (page * i) * onceGenNum : onceGenNum;
            List<ProductTraceCode> productTraceCodeList = new ArrayList<ProductTraceCode>(batchNum);
            for (int j = 0; j < batchNum; j++) {
                ProductTraceCode productTraceCode = new ProductTraceCode();
                productTraceCode.setMerchantId(productBatch.getMerchantId());
                productTraceCode.setBatchNo(productBatch.getBatchNo());
                productTraceCode.setTraceCode(UUID.randomUUID().toString());
                productTraceCode.setStatus(TracecodeState.normal.getValue());
                productTraceCodeList.add(productTraceCode);
            }
            productTraceCodeService.batchInsert(productTraceCodeList);
            productBatchMapper.updateHaveIssueNum(id, batchNum);
        }
    }
}
