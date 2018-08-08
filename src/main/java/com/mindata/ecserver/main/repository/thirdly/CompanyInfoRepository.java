package com.mindata.ecserver.main.repository.thirdly;

import com.mindata.ecserver.main.model.thirdly.CompanyIndustryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepository extends JpaRepository<CompanyIndustryInfo, Long> {

    CompanyIndustryInfo findByCompId(Long compId);

}
