package com.ejunhai.trace.system.dto;

import com.ejunhai.trace.system.model.SystemPrivilage;

public class SystemPrivilageDto extends SystemPrivilage {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	private String actionIds;

	public String getActionIds() {
		return actionIds;
	}

	public void setActionIds(String actionIds) {
		this.actionIds = actionIds;
	}

}
