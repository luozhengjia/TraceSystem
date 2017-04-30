package com.ejunhai.trace.product.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.trace.product.model.ProductBatch;
import com.ejunhai.trace.product.model.ProductInfo;

public class ProductUtil {
    public static Map<String, ProductInfo> getProductInfoMap(List<ProductInfo> productInfoList) {
        Map<String, ProductInfo> productInfoMap = new HashMap<String, ProductInfo>();
        if (CollectionUtils.isEmpty(productInfoList)) {
            return productInfoMap;
        }

        for (ProductInfo productInfo : productInfoList) {
            productInfoMap.put(String.valueOf(productInfo.getId()), productInfo);
        }

        return productInfoMap;
    }

    public static List<Integer> getProductIdList(List<ProductBatch> productBatchList) {
        Set<Integer> productIdSet = new HashSet<Integer>();
        if (CollectionUtils.isEmpty(productBatchList)) {
            return new ArrayList<Integer>(productIdSet);
        }

        for (ProductBatch productBatch : productBatchList) {
            if (productBatch.getProductId() != null) {
                productIdSet.add(productBatch.getProductId());
            }
        }
        return new ArrayList<Integer>(productIdSet);
    }

    public static List<Integer> getBaseIdList(List<ProductBatch> productBatchList) {
        Set<Integer> baseIdSet = new HashSet<Integer>();
        if (CollectionUtils.isEmpty(productBatchList)) {
            return new ArrayList<Integer>(baseIdSet);
        }

        for (ProductBatch productBatch : productBatchList) {
            if (productBatch.getBaseId() != null) {
                baseIdSet.add(productBatch.getBaseId());
            }
        }
        return new ArrayList<Integer>(baseIdSet);
    }

}
