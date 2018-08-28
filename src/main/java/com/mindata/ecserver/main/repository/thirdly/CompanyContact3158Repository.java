package com.mindata.ecserver.main.repository.thirdly;

import com.mindata.ecserver.main.model.thirdly.CompanyContact3158;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 58同城公司联系人
 *
 * @author HanLiqiang
 * @create 2018-08-07 下午2:13
 **/
public interface CompanyContact3158Repository extends JpaRepository<CompanyContact3158, Long> {


    /**
     *
     * @return List
     */
    List<CompanyContact3158> findAllByPhoneIsNotNull();
}
