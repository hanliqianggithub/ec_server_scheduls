package com.mindata.ecserver.main.manager;

import com.mindata.ecserver.global.util.CommonUtil;
import com.mindata.ecserver.main.model.thirdly.CompanyContact;
import com.mindata.ecserver.main.repository.thirdly.CompanyContactRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 58同城公司联系人
 *
 * @author HanLiqiang
 * @create 2018-08-07 下午2:56
 **/
@Service
public class CompanyContactManager {
    @Resource
    private CompanyContactRepository companyContactRepository;

    /**
     * 根据时间获取数据
     *
     * @return List
     */
    public List<CompanyContact> getCompanyContactListByUpdateTime() {
        return companyContactRepository.findByUpdateTimeLikeAndPhoneIsNotNull("%"+CommonUtil.getYesterday()+"%");
    }

    /**
     *
     * @return
     */
    public List<CompanyContact> getAll() {
        return companyContactRepository.findAllByPhoneIsNotNull();
    }
}

    

