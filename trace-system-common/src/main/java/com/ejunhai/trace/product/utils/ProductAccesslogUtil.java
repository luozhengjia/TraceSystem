package com.ejunhai.trace.product.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.trace.product.model.ProductAccessLog;

public class ProductAccesslogUtil {
    public static List<Integer> getProductIdList(List<ProductAccessLog> productAccessLogList) {
        Set<Integer> productIdSet = new HashSet<Integer>();
        if (CollectionUtils.isEmpty(productAccessLogList)) {
            return new ArrayList<Integer>(productIdSet);
        }

        for (ProductAccessLog productAccessLog : productAccessLogList) {
            if (productAccessLog.getProductId() != null) {
                productIdSet.add(productAccessLog.getProductId());
            }
        }
        return new ArrayList<Integer>(productIdSet);
    }

}
