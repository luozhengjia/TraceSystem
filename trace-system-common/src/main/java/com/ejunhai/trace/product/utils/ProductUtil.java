package com.ejunhai.trace.product.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

    private static final String CHARARTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 随机产生length位长度的字符串，每4位以空格分隔
     * 
     * @param length 兑换码长度
     * @return
     */
    public static String getCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int index = random.nextInt(36);
            if (i % 4 == 0)
                stringBuilder.append(CHARARTERS.charAt(index));
            else
                stringBuilder.append(CHARARTERS.charAt(index));
        }
        return stringBuilder.toString().trim();
    }

}
