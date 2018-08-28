package com.mindata.ecserver.main.model.thirdly;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author HanLiqiang
 * @create 2018-08-27 下午3:22
 **/
@Entity
@Table(name = "xs_company_contact_3158")
public class CompanyContact3158 {
    @Id
    private Long Id;

    private Long compId;

    private String phone;

    private String addr;
    private String contactPerson;

    private String comintro;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getComintro() {
        return comintro;
    }

    public void setComintro(String comintro) {
        this.comintro = comintro;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

    

