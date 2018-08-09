package com.mindata.ecserver.main.model.thirdly;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hanliqiang wrote on 2017/11/14
 */
@Entity
@Table(name = "xs_company_info_88")
public class CompanyIndustryInfo88 extends BaseCompanyIndustryInfo {

    private String companyName;

    private String contactPerson;

    private String phone;

    private String companyType;

    private String companyAddress;

    private String  updateTime;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
