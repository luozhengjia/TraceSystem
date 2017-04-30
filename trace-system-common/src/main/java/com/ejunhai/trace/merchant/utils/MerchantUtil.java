package com.ejunhai.trace.merchant.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.trace.merchant.model.MerchantInfo;
import com.ejunhai.trace.merchant.model.ProductionBaseInfo;

public class MerchantUtil {
    public static Map<String, MerchantInfo> getMerchantMap(List<MerchantInfo> merchantList) {
        Map<String, MerchantInfo> merchantMap = new HashMap<String, MerchantInfo>();
        if (CollectionUtils.isEmpty(merchantList)) {
            return merchantMap;
        }

        for (MerchantInfo merchant : merchantList) {
            merchantMap.put(String.valueOf(merchant.getId()), merchant);
        }

        return merchantMap;
    }

    public static Map<String, ProductionBaseInfo> getProductionBaseInfoMap(List<ProductionBaseInfo> productionBaseInfoList) {
        Map<String, ProductionBaseInfo> productionBaseInfoMap = new HashMap<String, ProductionBaseInfo>();
        if (CollectionUtils.isEmpty(productionBaseInfoList)) {
            return productionBaseInfoMap;
        }

        for (ProductionBaseInfo productionBaseInfo : productionBaseInfoList) {
            productionBaseInfoMap.put(String.valueOf(productionBaseInfo.getId()), productionBaseInfo);
        }

        return productionBaseInfoMap;
    }
}
