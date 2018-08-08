package com.mindata.ecserver.main.model.primary;

import java.util.Date;

/**
 * 企业信息
 *
 * @author HanLiqiang
 * @create 2018-08-08 下午2:54
 **/
public class Contact {
    private Long id;
    /**
     * 人名
     */
    private String name;
    /**
     * 公司名
     */
    private String company;
    /**
     * 是否是企业法人
     */
    private Integer legal;
    /**
     * 客户性别0无/1/男/2女
     */
    private Integer gender;
    private String mobile;
    private String phone;
    private String email;
    private String fax;
    private String province;
    private String city;
    private String title;
    private String qq;
    private String wechat;
    private String address;

    /**
     * 行业编码
     */
    private Integer vocation;
    /**
     * 官网
     */
    private String url;
    /**
     * 备注字段
     */
    private String memo;
    /**
     * 行业标签
     */
    private Integer vocationTag;
    /**
     * 人员数量
     */
    private Integer memberSizeTag;
    /**
     * 是否招聘销售
     */
    private Integer needSale;
    /**
     * 来源（58、桔子）
     */
    private Integer websiteId;
    /**
     * 推送的状态（0未推送，1成功，2失败，3删除）
     */
    private Integer state;
    /**
     * 对应爬虫的公司id
     */
    private Long compId;
    /**
     * ipc备案
     */
    private String ipcFlag;
    /**
     * 主要招聘岗位
     */
    private String mainJob;
    /**
     * 公司评分打分
     */
    private Double companyScore;

    private Date createTime;
    private Date insertTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getLegal() {
        return legal;
    }

    public void setLegal(Integer legal) {
        this.legal = legal;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getVocation() {
        return vocation;
    }

    public void setVocation(Integer vocation) {
        this.vocation = vocation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getVocationTag() {
        return vocationTag;
    }

    public void setVocationTag(Integer vocationTag) {
        this.vocationTag = vocationTag;
    }

    public Integer getMemberSizeTag() {
        return memberSizeTag;
    }

    public void setMemberSizeTag(Integer memberSizeTag) {
        this.memberSizeTag = memberSizeTag;
    }

    public Integer getNeedSale() {
        return needSale;
    }

    public void setNeedSale(Integer needSale) {
        this.needSale = needSale;
    }

    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public String getIpcFlag() {
        return ipcFlag;
    }

    public void setIpcFlag(String ipcFlag) {
        this.ipcFlag = ipcFlag;
    }

    public String getMainJob() {
        return mainJob;
    }

    public void setMainJob(String mainJob) {
        this.mainJob = mainJob;
    }

    public Double getCompanyScore() {
        return companyScore;
    }

    public void setCompanyScore(Double companyScore) {
        this.companyScore = companyScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}

    

