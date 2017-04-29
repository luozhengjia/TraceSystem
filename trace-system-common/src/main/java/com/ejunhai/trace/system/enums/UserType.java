package com.ejunhai.trace.system.enums;

/**
 * 用户类型
 * 
 * @author parcel
 */
public enum UserType {

    ssa(1, "超级管理员"), sa(2, "平台管理员"), sma(3, "企业管理员"), ma(4, "企业运营");

    private UserType(Integer flag, String title) {
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

    public static UserType get(Integer flag) {
        for (UserType temp : UserType.values()) {
            if (temp.flag.equals(flag)) {
                return temp;
            }
        }
        return null;
    }
}
