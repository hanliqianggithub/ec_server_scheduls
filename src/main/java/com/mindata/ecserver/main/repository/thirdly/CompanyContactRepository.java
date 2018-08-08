package com.mindata.ecserver.main.repository.thirdly;

import com.mindata.ecserver.main.model.thirdly.CompanyContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 58同城公司联系人
 *
 * @author HanLiqiang
 * @create 2018-08-07 下午2:13
 **/
public interface CompanyContactRepository extends JpaRepository<CompanyContact, Integer> {
    /**
     * 根据时间查询数据
     * @param updateTime updateTime
     * @return List
     */
    List<CompanyContact> findByUpdateTimeLikeAndPhoneIsNotNull(String updateTime);

    List<CompanyContact> findAllByPhoneIsNotNull();

}

    

