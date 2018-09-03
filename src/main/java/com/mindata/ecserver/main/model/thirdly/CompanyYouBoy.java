package com.mindata.ecserver.main.model.thirdly;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HanLiqiang
 * @create 2018-09-03 下午2:11
 **/
@Entity
@Table(name = "xs_company_info_youboy")
public class CompanyYouBoy {
    @Id
    private Long id;
    @Column(name = "comp_id")
    private Long compId;

    private String compArea;

    private String contactPerson;
    private String fax;
    private String phone;
    private String mobile;
    private String compAddr;
    private String updateTime;
    private String firmSize;
    private String sourceType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public String getCompArea() {
        return compArea;
    }

    public void setCompArea(String compArea) {
        this.compArea = compArea;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFirmSize() {
        return firmSize;
    }

    public void setFirmSize(String firmSize) {
        this.firmSize = firmSize;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}

    

