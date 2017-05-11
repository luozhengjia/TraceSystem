package com.ejunhai.trace.product.enums;

/**
 * 溯源码状态
 * 
 * @author parcel
 */
public enum TracecodeState {

	normal(1, "正常"), discard(2, "已作废");

	private TracecodeState(Integer flag, String title) {
		this.flag = flag;
		this.title = title;
	}

	private Integer flag;

	private String title;

	public Integer getValue() {
		return flag;
	}

	public String getTitle() {
		return title;
	}

	public static TracecodeState get(Integer flag) {
		for (TracecodeState temp : TracecodeState.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
