package com.mindata.ecserver.main.manager;

import com.mindata.ecserver.global.util.CommonUtil;
import com.mindata.ecserver.main.model.thirdly.CompanyIndustryInfo88;
import com.mindata.ecserver.main.repository.thirdly.CompanyIndustryInfo88Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 88
 *
 * @author HanLiqiang
 * @create 2018-08-09 下午2:08
 **/
@Service
public class CompanyInfo88Manager {
    @Resource
    private CompanyIndustryInfo88Repository companyIndustryInfo88Repository;

    /**
     * 查询昨天新增的数据
     * @return
     */
    public List<CompanyIndustryInfo88> findByUpdateTimeLikeAndPhoneIsNotNull(){
        return companyIndustryInfo88Repository.findByUpdateTimeLikeAndPhoneIsNotNull("%"+ CommonUtil.getYesterday()+"%");
    }

    public List<CompanyIndustryInfo88> findAllByPhoneIsNotNull(){
        return companyIndustryInfo88Repository.findAllByPhoneIsNotNull();
    }
}

    

