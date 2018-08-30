package com.mindata.ecserver.main.repository.primary;

import com.mindata.ecserver.main.model.primary.CompanyQichacha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author hanliqiang wrote on 2017/12/20
 */
public interface CompanyQichachaRepository extends JpaRepository<CompanyQichacha, Long> {

    /**
     * 根据公司名称查找行业描述
     *
     * @param companyName 公司名称
     * @return 结果
     */
    List<CompanyQichacha> findByCompanyName(String companyName);

    /**
     *
     * @return List
     */
    Page<CompanyQichacha> findByCompanyTelephoneNotOrCompanyTelephoneNot(String companyTelephone1, String companyTelephone2, Pageable pageable);

    /**
     *
     * @param companyTelephone1 companyTelephone1
     * @param companyTelephone2 companyTelephone2
     * @return Integer
     */
    Integer countByCompanyTelephoneNotOrCompanyTelephoneNot(String companyTelephone1,String companyTelephone2);
}
