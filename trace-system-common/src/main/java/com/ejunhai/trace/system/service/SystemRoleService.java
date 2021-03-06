package com.ejunhai.trace.system.service;

import java.util.List;

import com.ejunhai.trace.system.dto.SystemRoleDto;
import com.ejunhai.trace.system.model.SystemRole;

/**
 * 
 * SystemRole Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface SystemRoleService {

	/**
	 * 根据Id获取SystemRole
	 * 
	 * @param id
	 * @return
	 */
	public SystemRole read(Integer id);

	/**
	 * 新增SystemRole
	 * 
	 * @param systemRole
	 */
	public void insert(SystemRole systemRole);

	/**
	 * 更新SystemRole
	 * 
	 * @param systemRole
	 */
	public void update(SystemRole systemRole);

	/**
	 * 删除SystemRole
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 查询SystemRole数量
	 * 
	 * @param systemRole
	 * @return
	 */
	public Integer querySystemRoleCount(SystemRoleDto systemRoleDto);

	/**
	 * 查询SystemRole列表
	 * 
	 * @param systemRole
	 * @return
	 */
	public List<SystemRole> querySystemRoleList(SystemRoleDto systemRoleDto);

	/**
	 * 根据ID批量获取角色信息
	 * 
	 * @param idList
	 * @return
	 */
	public List<SystemRole> getSystemRoleListByIds(List<Integer> idList);

}
