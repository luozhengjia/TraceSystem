package com.ejunhai.trace.system.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.trace.common.utils.CommonUtils;
import com.ejunhai.trace.system.model.SystemUser;

public class SystemUserUtil {

    /**
     * 获取role Id list
     * 
     * @param systemPrivilageList
     * @return
     */
    public static List<Integer> getRoleIdList(List<SystemUser> systemUserList) {
        Set<Integer> roleIdSet = new HashSet<Integer>();
        if (CollectionUtils.isEmpty(systemUserList)) {
            return new ArrayList<Integer>(roleIdSet);
        }

        for (SystemUser systemUser : systemUserList) {
            roleIdSet.addAll(CommonUtils.str2IntList(systemUser.getRoleIds(), ","));
        }

        return new ArrayList<Integer>(roleIdSet);
    }

    public static List<Integer> getMerchantIdList(List<SystemUser> systemUserList) {
        Set<Integer> merchantIdSet = new HashSet<Integer>();
        if (CollectionUtils.isEmpty(systemUserList)) {
            return new ArrayList<Integer>(merchantIdSet);
        }

        for (SystemUser systemUser : systemUserList) {
            if (systemUser.getMerchantId() != null) {
                merchantIdSet.add(systemUser.getMerchantId());
            }
        }
        return new ArrayList<Integer>(merchantIdSet);
    }

    public static Map<String, SystemUser> getSystemUserMap(List<SystemUser> systemUserList) {
        Map<String, SystemUser> systemUserMap = new HashMap<String, SystemUser>();
        if (CollectionUtils.isEmpty(systemUserList)) {
            return systemUserMap;
        }

        for (SystemUser systemUser : systemUserList) {
            systemUserMap.put(String.valueOf(systemUser.getId()), systemUser);
        }

        return systemUserMap;
    }
}
