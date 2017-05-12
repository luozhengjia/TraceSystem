package com.ejunhai.trace.system.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.trace.system.dao.SystemOperateLogMapper;
import com.ejunhai.trace.system.model.SystemOperateLog;
import com.ejunhai.trace.system.service.SystemOperateLogService;

/**
 * SystemOperateLog Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
@Service("systemOperateLogService")
public class SystemOperateLogServiceImpl implements SystemOperateLogService {

    @Resource
    private SystemOperateLogMapper systemOperateLogMapper;

    @Override
    public void log(Integer merchantId, String desc, Integer creator) {
        SystemOperateLog systemOperateLog = new SystemOperateLog();
        systemOperateLog.setMerchantId(merchantId);
        systemOperateLog.setUserId(creator);
        systemOperateLog.setOperation(desc);
        systemOperateLog.setOperType(0);
        systemOperateLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        systemOperateLogMapper.insert(systemOperateLog);
    }

    @Override
    public Integer querySystemOperateLogCount(SystemOperateLog systemOperateLog) {
        return systemOperateLogMapper.querySystemOperateLogCount(systemOperateLog);
    }

    @Override
    public List<SystemOperateLog> querySystemOperateLogList(SystemOperateLog systemOperateLog) {
        return systemOperateLogMapper.querySystemOperateLogList(systemOperateLog);
    }

}
