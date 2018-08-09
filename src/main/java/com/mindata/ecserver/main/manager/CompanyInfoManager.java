package com.mindata.ecserver.main.manager;

import com.mindata.ecserver.main.model.thirdly.CompanyIndustryInfo;
import com.mindata.ecserver.main.repository.thirdly.CompanyInfoRepository;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        List<CompanyIndustryInfo> companyIndustryInfos = companyInfoRepository.findByCompId(compId);
        if(CollectionUtil.isNotEmpty(companyIndustryInfos)){
            String firmSize = companyIndustryInfos.get(0).getFirmSize();
            return cutCodeSizeName(firmSize);
        }

        return cutCodeSizeName(null);
    }
}

    

