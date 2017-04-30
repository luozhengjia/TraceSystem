package com.ejunhai.trace.system.service;

import java.util.List;

import com.ejunhai.trace.system.dto.SystemUserDto;
import com.ejunhai.trace.system.model.SystemAction;
import com.ejunhai.trace.system.model.SystemUser;

/**
 * 
 * SystemUser Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface SystemUserService {

    /**
     * 根据Id获取SystemUser
     * 
     * @param id
     * @return
     */
    public SystemUser read(Integer id);

    /**
     * 新增SystemUser
     * 
     * @param systemUser
     */
    public void insert(SystemUser systemUser);

    /**
     * 更新SystemUser
     * 
     * @param systemUser
     */
    public void update(SystemUser systemUser);

    /**
     * 删除SystemUser
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询SystemUser数量
     * 
     * @param systemUserDto
     * @return
     */
    public Integer querySystemUserCount(SystemUserDto systemUserDto);

    /**
     * 查询SystemUser列表
     * 
     * @param systemUserDto
     * @return
     */
    public List<SystemUser> querySystemUserList(SystemUserDto systemUserDto);

    /**
     * 根据登录名获取系统用户
     * 
     * @param loginName
     * @return
     */
    public SystemUser getSystemUserByLoginName(String loginName);

    /**
     * @param userIds
     * @return
     */
    public List<SystemUser> getSystemUserListByUserIds(List<Integer> userIds);

    /**
     * 用户授权
     * 
     * @param userId
     * @return
     */
    public List<SystemAction> authorize(Integer userId);

}
