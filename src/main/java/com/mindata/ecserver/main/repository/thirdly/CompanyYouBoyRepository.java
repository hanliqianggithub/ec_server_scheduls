package com.mindata.ecserver.main.repository.thirdly;

import com.mindata.ecserver.main.model.thirdly.CompanyYouBoy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HanLiqiang
 * @create 2018-09-03 下午2:20
 **/
public interface CompanyYouBoyRepository extends JpaRepository<CompanyYouBoy, Long> {

    Integer countByPhoneNot(String phone);

    Page<CompanyYouBoy> findByPhoneNot(String phone,Pageable pageable);
}

    

