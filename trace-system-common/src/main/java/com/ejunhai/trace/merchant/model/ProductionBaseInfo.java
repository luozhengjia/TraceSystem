package com.ejunhai.trace.merchant.model;

import java.io.Serializable;
import java.util.Date;

public class ProductionBaseInfo implements Serializable {
    private Integer id;

    private Integer merchantId;

    private String baseName;

    private String baseType;

    private String baseArea;

    private String baseAddress;

    private String baseEnvInfo;

    private String baseResourceInfo;

    private String baseBulidTime;

    private String picUrls;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName == null ? null : baseName.trim();
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType == null ? null : baseType.trim();
    }

    public String getBaseArea() {
        return baseArea;
    }

    public void setBaseArea(String baseArea) {
        this.baseArea = baseArea == null ? null : baseArea.trim();
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress == null ? null : baseAddress.trim();
    }

    public String getBaseEnvInfo() {
        return baseEnvInfo;
    }

    public void setBaseEnvInfo(String baseEnvInfo) {
        this.baseEnvInfo = baseEnvInfo == null ? null : baseEnvInfo.trim();
    }

    public String getBaseResourceInfo() {
        return baseResourceInfo;
    }

    public void setBaseResourceInfo(String baseResourceInfo) {
        this.baseResourceInfo = baseResourceInfo == null ? null : baseResourceInfo.trim();
    }

    public String getBaseBulidTime() {
        return baseBulidTime;
    }

    public void setBaseBulidTime(String baseBulidTime) {
        this.baseBulidTime = baseBulidTime;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls == null ? null : picUrls.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", baseName=").append(baseName);
        sb.append(", baseType=").append(baseType);
        sb.append(", baseArea=").append(baseArea);
        sb.append(", baseAddress=").append(baseAddress);
        sb.append(", baseEnvInfo=").append(baseEnvInfo);
        sb.append(", baseResourceInfo=").append(baseResourceInfo);
        sb.append(", baseBulidTime=").append(baseBulidTime);
        sb.append(", picUrls=").append(picUrls);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}