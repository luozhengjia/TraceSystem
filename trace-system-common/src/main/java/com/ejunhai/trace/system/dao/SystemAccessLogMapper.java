package com.ejunhai.trace.system.dao;

import java.util.List;

import com.ejunhai.trace.system.dto.SystemAccessLogDto;
import com.ejunhai.trace.system.model.SystemAccessLog;

public interface SystemAccessLogMapper {

    /**
     * 根据Id获取SystemAccessLog
     * 
     * @param id
     * @return
     */
    public SystemAccessLog read(Integer id);

    /**
     * 新增SystemAccessLog
     * 
     * @param merchant
     */
    public void insert(SystemAccessLog systemAccessLog);

    /**
     * 更新SystemAccessLog
     * 
     * @param merchant
     */
    public void update(SystemAccessLog systemAccessLog);

    /**
     * 删除SystemAccessLog
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询SystemAccessLog数量
     * 
     * @param merchant
     * @return
     */
    public Integer querySystemAccessLogCount(SystemAccessLogDto systemAccessLogDto);

    /**
     * 查询SystemAccessLog列表
     * 
     * @param merchant
     * @return
     */
    public List<SystemAccessLog> querySystemAccessLogList(SystemAccessLogDto systemAccessLogDto);
}