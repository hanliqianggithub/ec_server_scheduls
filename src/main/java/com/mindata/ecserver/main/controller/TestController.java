package com.mindata.ecserver.main.controller;

import com.mindata.ecserver.global.http.response.BaseData;
import com.mindata.ecserver.main.manager.ContactManager;
import com.mindata.ecserver.main.service.CompanyCoordinateService;
import com.mindata.ecserver.main.service.EsContactService;
import com.mindata.ecserver.main.service.FetchCompanyPhoneHistoryService;
import com.mindata.ecserver.main.service.PtCustomerStateService;
import com.mindata.ecserver.schedel.DealCustomerSchedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author wuweifeng wrote on 2017/11/7.
 */
@RestController
public class TestController {
    @Resource
    private FetchCompanyPhoneHistoryService fetchCompanyPhoneHistoryService;
    @Resource
    private EsContactService esContactService;
    @Resource
    private CompanyCoordinateService coordinateService;
    @Resource
    private PtCustomerStateService ptCustomerStateService;
    @Resource
    private DealCustomerSchedule dealCustomerSchedule;
    @Resource
    private ContactManager contactManager;

    @GetMapping("/fetch")
    public BaseData fetchCompanyHistory() throws IOException {
        return fetchCompanyPhoneHistoryService.fetch();
    }

    @GetMapping("/push")
    public Object pushDbToEs() {
        esContactService.dbToEs(false);
        return "dbToEs";
    }

    @GetMapping("/push/between")
    public Object pushIdBetweenDbToEs(Long beginId, Long endId) {
        esContactService.partInsertBetween(beginId, endId);
        return "between dbToEs";
    }

    @GetMapping("/force")
    public Object forceDbToEs() {
        esContactService.forceTotal();
        return "force dbToEs";
    }

    @GetMapping("/baidu")
    public String baidu(Boolean force) throws IOException {
        coordinateService.completeAllCompanyCoordinate(force);
        return "baidu";
    }

    @GetMapping("/customer")
    public String customer() {
        dealCustomerSchedule.executeDealCustomerOperationTask();
        return "customer";
    }
    @GetMapping("/test")
    public String test() {
        contactManager.fetchYouboyContact();
        return "customer";
    }
}
