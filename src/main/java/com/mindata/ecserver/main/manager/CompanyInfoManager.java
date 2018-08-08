package com.mindata.ecserver.main.manager;

import com.mindata.ecserver.global.util.CommonUtil;
import com.mindata.ecserver.main.model.thirdly.CompanyIndustryInfo;
import com.mindata.ecserver.main.repository.thirdly.CompanyInfoRepository;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.mindata.ecserver.global.util.CommonUtil.cutCodeSizeName;

/**
 * 58公司信息
 *
 * @author HanLiqiang
 * @create 2018-08-07 下午7:03
 **/
@Service
public class CompanyInfoManager {
    @Resource
    private CompanyInfoRepository companyInfoRepository;

    public Integer getSizeId(Long compId){
        CompanyIndustryInfo companyIndustryInfo = companyInfoRepository.findByCompId(compId);
        if(ObjectUtil.isNotNull(companyIndustryInfo)){
            String firmSize = companyIndustryInfo.getFirmSize();
            return cutCodeSizeName(firmSize);
        }

        return cutCodeSizeName(null);
    }
}

    

