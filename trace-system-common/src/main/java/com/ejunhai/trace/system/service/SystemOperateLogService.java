package com.ejunhai.trace.system.service;

import java.util.List;

import com.ejunhai.trace.system.model.SystemOperateLog;

/**
 * 
 * SystemOperateLog Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface SystemOperateLogService {

    public void log(Integer merchantId, String desc, Integer creator);

    /**
     * 查询SystemOperateLog数量
     * 
     * @param systemOperateLog
     * @return
     */
    public Integer querySystemOperateLogCount(SystemOperateLog systemOperateLog);

    /**
     * 查询SystemOperateLog列表
     * 
     * @param systemOperateLog
     * @return
     */
    public List<SystemOperateLog> querySystemOperateLogList(SystemOperateLog systemOperateLog);

}
