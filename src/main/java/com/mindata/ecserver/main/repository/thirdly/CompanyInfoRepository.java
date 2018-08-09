package com.mindata.ecserver.main.repository.thirdly;

import com.mindata.ecserver.main.model.thirdly.CompanyIndustryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyInfoRepository extends JpaRepository<CompanyIndustryInfo, Long> {

    List<CompanyIndustryInfo> findByCompId(Long compId);

}
