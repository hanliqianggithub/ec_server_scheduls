package com.mindata.ecserver.main.manager;

import com.mindata.ecserver.global.util.CommonUtil;
import com.mindata.ecserver.main.model.es.EsContact;
import com.mindata.ecserver.main.model.primary.Contact;
import com.mindata.ecserver.main.model.primary.EcContactEntity;
import com.mindata.ecserver.main.model.thirdly.CompanyContact;
import com.mindata.ecserver.main.model.thirdly.CompanyContact3158;
import com.mindata.ecserver.main.model.thirdly.CompanyIndustryInfo88;
import com.mindata.ecserver.main.model.thirdly.CompanyJobInfo;
import com.mindata.ecserver.main.repository.primary.EcContactRepository;
import com.mindata.ecserver.main.repository.thirdly.CompanyContact3158Repository;
import com.mindata.ecserver.main.repository.thirdly.CompanyJobInfoRepository;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.mindata.ecserver.global.Constant.DOUHAO;
import static com.mindata.ecserver.global.Constant.STATE_NORMAL;
import static com.mindata.ecserver.global.util.CommonUtil.reviseFixedTelephone;

/**
 * @author wuweifeng wrote on 2017/11/9.
 */
@Service
public class ContactManager {
    @Resource
    private EcContactRepository ecContactRepository;
    @Resource
    private EcCodeAreaManager ecCodeAreaManager;
    @Resource
    private CompanyIndustryInfoManager companyIndustryInfoManager;
    @Resource
    private EsVocationCodeManager esVocationCodeManager;
    @Resource
    private EsContactManager esContactManager;
    @Resource
    private CompanyContactManager companyContactManager;
    @Resource
    private CompanyCodeManager companyCodeManager;

    @Resource
    private CompanyInfoManager companyInfoManager;
    @Resource
    private CompanyJobInfoRepository companyJobInfoRepository;
    @Resource
    private CompanyInfo88Manager companyInfo88Manager;
    @Resource
    private CompanyContact3158Repository companyContact3158Repository;


    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());


    /**
     * 计算两个id间的数量
     *
     * @param beginId begin
     * @param endId   end
     * @return 数量
     */
    public Long countIdBetween(Long beginId, Long endId) {
        return ecContactRepository.countByIdBetween(beginId, endId);
    }

    public Page<EcContactEntity> findContact(Pageable pageable) {
        return ecContactRepository.findAll(pageable);
    }

    /**
     * 分页查询所有
     *
     * @param pageable pageable
     * @return 分页结果
     */
    public Page<EcContactEntity> findAll(Pageable pageable) {
        return ecContactRepository.findAll(pageable);
    }

    /**
     * 查询没有省市code的
     *
     * @param pageable 分页
     * @return 结果
     */
    private Page<EcContactEntity> findContactByProvince(String province, Pageable pageable) {
        return ecContactRepository.findByProvince(province, pageable);
    }

    /**
     * 查询createTime晚于目标时间的
     */
    public Page<EcContactEntity> findContactByCreateTimeAfter(Date createTime, Pageable pageable) {
        return ecContactRepository.findByCreateTimeAfter(createTime, pageable);
    }

    /**
     * 查询id大于某个id且目标时间早于晚上12点前的
     */
    public Page<EcContactEntity> findByIdGreaterThanAndCreateTimeLessThan(Long id, Date endTime, Pageable pageable) {
        return ecContactRepository.findByIdGreaterThanAndCreateTimeLessThan(id, endTime, pageable);
    }

    /**
     * 查询id大于某个id的
     */
    public Page<EcContactEntity> findByIdGreaterThan(Long id, Pageable pageable) {
        return ecContactRepository.findByIdGreaterThan(id, pageable);
    }

    /**
     * 查询id在某个范围的
     */
    public Page<EcContactEntity> findByIdBetween(Long beginId, Long endId, Pageable pageable) {
        return ecContactRepository.findByIdBetween(beginId, endId, pageable);
    }

    /**
     * 查询最新的一条
     */
    public EcContactEntity findLastOne() {
        Pageable pageable = new PageRequest(0, 1, Sort.Direction.DESC, "id");
        return ecContactRepository.findAll(pageable).getContent().get(0);
    }

    /**
     * 查询第一条
     */
    public EcContactEntity findFirstOne() {
        Pageable pageable = new PageRequest(0, 1, Sort.Direction.ASC, "id");
        return ecContactRepository.findAll(pageable).getContent().get(0);
    }

    public Integer deleteByIds(String ids) {
        if (ids.endsWith(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        return ecContactRepository.updateState(ids);
    }

    /**
     * 该方法是更新老数据的行业和省市信息的
     * 补齐省市县code表
     */
    public void completeAreaCode() {
        LOGGER.info("开始补齐省市县");
        Pageable pageable = new PageRequest(0, 100, Sort.Direction.ASC, "id");
        Page<EcContactEntity> ecContactEntities = findContactByProvince("0", pageable);
        int total = ecContactEntities.getTotalPages();
        for (int i = 0; i < total; i++) {
            Page<EcContactEntity> entities = findContactByProvince("0", pageable);
            for (EcContactEntity ecContactEntity : entities.getContent()) {
                HashMap<String, Integer> map = ecCodeAreaManager.findAreaCode(ecContactEntity.getAddress());
                ecContactEntity.setProvince(map.get("province") + "");
                ecContactEntity.setCity(map.get("city") + "");
                ecContactRepository.save(ecContactEntity);
                EsContact esContact = esContactManager.findById(ecContactEntity.getId());
                if (esContact != null) {
                    LOGGER.info("es在更新id为" + ecContactEntity.getId() + "的地址信息");
                    esContact.setProvince(map.get("province"));
                    esContact.setCity(map.get("city"));
                    esContact.setAddress(ecContactEntity.getAddress());
                    esContactManager.save(esContact);
                }
            }
        }
        LOGGER.info("补齐完毕");
    }

    /**
     * 根据状态查询所有正常状态的公司
     */
    public Page<EcContactEntity> findByState(Pageable pageable) {
        return ecContactRepository.findByState(STATE_NORMAL, pageable);
    }

    /**
     * 更新code值
     *
     * @param compId 公司Id
     */
    private void updateVocationCode(Long compId, String companyName) {
        List<String> industryList = companyIndustryInfoManager.getIndustryInfoForDb(compId, companyName);
        HashMap<String, Integer> map = esVocationCodeManager.findByVocationName(industryList.get(0));
        Integer vocationCode = map.get("vocationCode");
        Integer num = ecContactRepository.updateCodeByVocationName(vocationCode, compId);
        if (num > 0) {
            LOGGER.info("更新成功" + num + "条vocationCode");
        }
    }

    /**
     * 查询某段时间内的数据
     */
    public Page<EcContactEntity> findByDateBetween(Date begin, Date end, Pageable pageable) {
        return ecContactRepository.findByCreateTimeBetween(begin, end, pageable);
    }


    public EcContactEntity findOne(Long id) {
        return ecContactRepository.findById(id);
    }

    public void fetch58Contact() {
        StringBuilder jobName = new StringBuilder();
        List<CompanyContact> contactList = companyContactManager.getCompanyContactListByUpdateTime();

        for (CompanyContact companyContact : contactList) {
            String companyName = companyCodeManager.getNameById(companyContact.getCompId());

            Integer count = ecContactRepository.countByMobileAndPhone(
                    CommonUtil.reviseMobile(companyContact.getPhone()), reviseFixedTelephone(companyContact.getPhone()));

            List<CompanyJobInfo> list = companyJobInfoRepository.findByCompId(companyContact.getCompId());
            for (CompanyJobInfo companyJobInfo : list) {
                if (StrUtil.isNotEmpty(companyJobInfo.getJobName())) {
                    jobName.append(companyJobInfo.getJobName()).append(DOUHAO);
                }
            }
            System.out.println(count + "bbbbbbbbbbbbbbb");

            if (count == 0 && StringUtils.isNotEmpty(companyName)) {
                LOGGER.info("compId为：" + companyContact.getCompId());
                System.out.println(companyContact.getCompId() + "aaaaaaaaaaaaaaa");
                Integer province = 0;
                Integer city = 0;
                if (StringUtils.isNotEmpty(companyContact.getAddr())) {
                    HashMap<String, Integer> map = ecCodeAreaManager.findAreaCode(companyContact.getAddr());
                    province = map.get("province");
                    city = map.get("city");
                }
                List<String> industryList = companyIndustryInfoManager.getIndustryInfoForDb(companyContact.getCompId(), companyName);
                Integer vocationCode = esVocationCodeManager.findByVocationName(industryList.get(0)).get("vocationCode");
                Integer webSiteId = 1;
                ////////////////////////
                EcContactEntity ecContactEntity = new EcContactEntity();
                ecContactEntity.setName(companyContact.getContactPerson() == null ? "" : companyContact.getContactPerson());
                ecContactEntity.setCompany(companyName);
                ecContactEntity.setLegal(0);
                if (jobName.toString().contains("销售")) {
                    ecContactEntity.setNeedSale(1);
                } else {
                    ecContactEntity.setNeedSale(0);
                }
                ecContactEntity.setGender(0);
                ecContactEntity.setMemberSizeTag(companyInfoManager.getSizeId(companyContact.getCompId()));
                ecContactEntity.setMobile(CommonUtil.reviseMobile(companyContact.getPhone()));
                ecContactEntity.setPhone(reviseFixedTelephone(companyContact.getPhone()));
                ecContactEntity.setWebsiteId(webSiteId);
                ecContactEntity.setProvince(province.toString());
                ecContactEntity.setCity(city.toString());
                ecContactEntity.setAddress(companyContact.getAddr() == null ? "" : companyContact.getAddr());
                ecContactEntity.setVocation(vocationCode);
                ecContactEntity.setState(0);
                ecContactEntity.setCompId(companyContact.getCompId());
                ecContactEntity.setCreateTime(new Date());
                ecContactEntity.setInsertTime(new Date());
                ecContactEntity.setCompanyScore(0.00);
                ecContactEntity.setIpcFlag("");
                ecContactEntity.setMainJob("");
                ecContactRepository.save(ecContactEntity);
            }
        }
    }

    public void fetch88Contact() {
        StringBuilder jobName = new StringBuilder();
        List<CompanyIndustryInfo88> contactList = companyInfo88Manager.findAllByPhoneIsNotNull();
        for (CompanyIndustryInfo88 companyInfo : contactList) {
            String companyName = companyCodeManager.getNameById(companyInfo.getCompId());
            Integer count = ecContactRepository.countByMobileAndPhone(
                    CommonUtil.reviseMobile(companyInfo.getPhone()), reviseFixedTelephone(companyInfo.getPhone()));
            LOGGER.info("count：" + count);
            List<CompanyJobInfo> list = companyJobInfoRepository.findByCompId(companyInfo.getCompId());
            for (CompanyJobInfo companyJobInfo : list) {
                if (StrUtil.isNotEmpty(companyJobInfo.getJobName())) {
                    jobName.append(companyJobInfo.getJobName()).append(DOUHAO);
                }
            }
            if (count == 0 && StringUtils.isNotEmpty(companyName) && companyInfo.getPhone() != "0000") {
                LOGGER.info("compId为：" + companyInfo.getCompId());
                Integer province = 0;
                Integer city = 0;
                if (StringUtils.isNotEmpty(companyInfo.getCompanyAddress())) {
                    HashMap<String, Integer> map = ecCodeAreaManager.findAreaCode(companyInfo.getCompanyAddress());
                    province = map.get("province");
                    city = map.get("city");
                }
                List<String> industryList = companyIndustryInfoManager.getIndustryInfoForDb(companyInfo.getCompId(), companyName);
                Integer vocationCode = esVocationCodeManager.findByVocationName(industryList.get(0)).get("vocationCode");
                Integer webSiteId = 3;
                EcContactEntity ecContactEntity = new EcContactEntity();
                ecContactEntity.setName(companyInfo.getContactPerson() == null ? "" : companyInfo.getContactPerson());
                ecContactEntity.setCompany(companyName);
                ecContactEntity.setLegal(0);
                if (jobName.toString().contains("销售")) {
                    ecContactEntity.setNeedSale(1);
                } else {
                    ecContactEntity.setNeedSale(0);
                }
                ecContactEntity.setGender(0);
                ecContactEntity.setMemberSizeTag(companyInfoManager.getSizeId(companyInfo.getCompId()));
                ecContactEntity.setMobile(CommonUtil.reviseMobile(companyInfo.getPhone()));
                ecContactEntity.setPhone(reviseFixedTelephone(companyInfo.getPhone()));
                ecContactEntity.setWebsiteId(webSiteId);
                ecContactEntity.setProvince(province.toString());
                ecContactEntity.setCity(city.toString());
                ecContactEntity.setAddress(companyInfo.getCompanyAddress() == null ? "" : companyInfo.getCompanyAddress());
                ecContactEntity.setVocation(vocationCode);
                ecContactEntity.setState(0);
                ecContactEntity.setCompId(companyInfo.getCompId());
                ecContactEntity.setCreateTime(new Date());
                ecContactEntity.setInsertTime(new Date());
                ecContactEntity.setCompanyScore(0.00);
                ecContactEntity.setIpcFlag("");
                ecContactEntity.setMainJob("");
                ecContactRepository.save(ecContactEntity);
            }
        }
    }

    public void fetch3158Contact() {
        List<CompanyContact3158> contactList = companyContact3158Repository.findAllByPhoneIsNotNull();
        for (CompanyContact3158 companyInfo : contactList) {
            String companyName = companyCodeManager.getNameById(companyInfo.getCompId());
            Integer count = ecContactRepository.countByMobileAndPhone(
                    CommonUtil.reviseMobile(companyInfo.getPhone()), reviseFixedTelephone(companyInfo.getPhone()));
            LOGGER.info("count：" + count);
            if (count == 0 && StringUtils.isNotEmpty(companyName) && companyInfo.getPhone() != "0000") {
                LOGGER.info("compId为：" + companyInfo.getCompId());
                Integer province = 0;
                Integer city = 0;
                if (StringUtils.isNotEmpty(companyInfo.getAddr())) {
                    HashMap<String, Integer> map = ecCodeAreaManager.findAreaCode(companyInfo.getAddr());
                    province = map.get("province");
                    city = map.get("city");
                }
                List<String> industryList = companyIndustryInfoManager.getIndustryInfoForDb(companyInfo.getCompId(), companyName);
                Integer vocationCode = esVocationCodeManager.findByVocationName(industryList.get(0)).get("vocationCode");
                Integer webSiteId = 2;
                EcContactEntity ecContactEntity = new EcContactEntity();
                ecContactEntity.setName(companyInfo.getContactPerson() == null ? "" : companyInfo.getContactPerson());
                ecContactEntity.setCompany(companyName);
                ecContactEntity.setLegal(0);
                ecContactEntity.setNeedSale(0);
                ecContactEntity.setGender(0);
                ecContactEntity.setMemberSizeTag(companyInfoManager.getSizeId(companyInfo.getCompId()));
                ecContactEntity.setMobile(CommonUtil.reviseMobile(companyInfo.getPhone()));
                ecContactEntity.setPhone(reviseFixedTelephone(companyInfo.getPhone()));
                ecContactEntity.setWebsiteId(webSiteId);
                ecContactEntity.setProvince(province.toString());
                ecContactEntity.setCity(city.toString());
                ecContactEntity.setAddress(companyInfo.getAddr() == null ? "" : companyInfo.getAddr());
                ecContactEntity.setVocation(vocationCode);
                ecContactEntity.setState(0);
                ecContactEntity.setCompId(companyInfo.getCompId());
                ecContactEntity.setCreateTime(new Date());
                ecContactEntity.setInsertTime(new Date());
                ecContactEntity.setCompanyScore(0.00);
                ecContactEntity.setIpcFlag("");
                ecContactEntity.setMainJob("");
                ecContactRepository.save(ecContactEntity);
            }
        }
    }


    public static void main(String[] args) {

    }
}
