package com.mindata.ecserver.main.manager;

import com.mindata.ecserver.main.model.thirdly.CompanyCode;
import com.mindata.ecserver.main.repository.thirdly.CompanyCodeRepository;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 公司名称
 *
 * @author HanLiqiang
 * @create 2018-08-07 下午3:26
 **/
@Service
public class CompanyCodeManager {
    @Resource
    private CompanyCodeRepository companyCodeRepository;

    /**
     * 根据id获取名称
     *
     * @param id id
     * @return String
     */
    public String getNameById(Long id) {
        CompanyCode companyCode = companyCodeRepository.findOne(id);
        return ObjectUtil.isNull(companyCode) ? null : companyCode.getCompName();
    }
}

    

