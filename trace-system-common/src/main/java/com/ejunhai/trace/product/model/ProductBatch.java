package com.ejunhai.trace.product.model;

import java.io.Serializable;
import java.util.Date;

public class ProductBatch implements Serializable {
    private Integer id;

    private Integer merchantId;

    private String batchNo;

    private Integer productId;

    private Integer baseId;

    private String productionDate;

    private String expireTime;

    private Integer issueAmount;

    private Integer hasIssueNum;

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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate == null ? null : productionDate.trim();
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime == null ? null : expireTime.trim();
    }

    public Integer getIssueAmount() {
        return issueAmount;
    }

    public void setIssueAmount(Integer issueAmount) {
        this.issueAmount = issueAmount;
    }

    public Integer getHasIssueNum() {
        return hasIssueNum;
    }

    public void setHasIssueNum(Integer hasIssueNum) {
        this.hasIssueNum = hasIssueNum;
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
        sb.append(", batchNo=").append(batchNo);
        sb.append(", productId=").append(productId);
        sb.append(", baseId=").append(baseId);
        sb.append(", productionDate=").append(productionDate);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", issueAmount=").append(issueAmount);
        sb.append(", hasIssueNum=").append(hasIssueNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}