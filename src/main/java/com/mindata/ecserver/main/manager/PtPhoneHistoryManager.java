package com.mindata.ecserver.main.manager;

import com.mindata.ecserver.global.util.CommonUtil;
import com.mindata.ecserver.main.repository.secondary.PtPhoneHistoryRepository;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wuweifeng wrote on 2017/12/28.
 */
@Component
public class PtPhoneHistoryManager {
    @Resource
    private PtPhoneHistoryRepository ptPhoneHistoryRepository;

    public Integer intentCountByCrmId(Long crmId, Date begin, Date end) {
        return ptPhoneHistoryRepository.findIntentedCountByCrmId(crmId, begin, end);
    }

    public boolean isIntent(Long crmId, Date operateTime) {
        String date = DateUtil.formatDate(operateTime);
        Date begin = CommonUtil.beginOfDay(date);
        Date end = CommonUtil.endOfDay(date);

        Integer integer = intentCountByCrmId(crmId, begin, end);
        if (integer == null) {
            return false;
        }
        return integer >= 1;
    }
}