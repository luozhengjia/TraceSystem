package com.ejunhai.trace.merchant.model;

import java.io.Serializable;
import java.util.Date;

public class MerchantInfo implements Serializable {
    private Integer id;

    private String merchantName;

    private String businessLine;

    private Byte merchantLevel;

    private Date openTime;

    private Date expireTime;

    private Integer availableSmsNum;

    private String organization;

    private String businessLicense;

    private String recordNumber;

    private String contacts;

    private String telephone;

    private String address;

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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine == null ? null : businessLine.trim();
    }

    public Byte getMerchantLevel() {
        return merchantLevel;
    }

    public void setMerchantLevel(Byte merchantLevel) {
        this.merchantLevel = merchantLevel;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getAvailableSmsNum() {
        return availableSmsNum;
    }

    public void setAvailableSmsNum(Integer availableSmsNum) {
        this.availableSmsNum = availableSmsNum;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber == null ? null : recordNumber.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        sb.append(", merchantName=").append(merchantName);
        sb.append(", businessLine=").append(businessLine);
        sb.append(", merchantLevel=").append(merchantLevel);
        sb.append(", openTime=").append(openTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", availableSmsNum=").append(availableSmsNum);
        sb.append(", organization=").append(organization);
        sb.append(", businessLicense=").append(businessLicense);
        sb.append(", recordNumber=").append(recordNumber);
        sb.append(", contacts=").append(contacts);
        sb.append(", telephone=").append(telephone);
        sb.append(", address=").append(address);
        sb.append(", picUrls=").append(picUrls);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}