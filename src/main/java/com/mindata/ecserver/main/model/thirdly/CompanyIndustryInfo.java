package com.mindata.ecserver.main.model.thirdly;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author hanliqiang wrote on 2017/11/14
 */
@Entity
@Table(name = "xs_company_info")
public class CompanyIndustryInfo extends BaseCompanyIndustryInfo {

    private String firmSize;

    public String getFirmSize() {
        return firmSize;
    }

    public void setFirmSize(String firmSize) {
        this.firmSize = firmSize;
    }
}
