package com.ejunhai.trace.merchant.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.trace.merchant.model.MerchantInfo;

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
}
