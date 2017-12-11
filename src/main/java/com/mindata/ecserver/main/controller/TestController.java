package com.mindata.ecserver.main.controller;

import com.mindata.ecserver.global.http.response.BaseData;
import com.mindata.ecserver.main.service.EsContactService;
import com.mindata.ecserver.main.service.FetchCompanyPhoneHistoryService;
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

    @GetMapping("/test1")
    public Object test1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "dbToEs";
    }

    @GetMapping("/test2")
    public Object test2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "dbToEs";
    }

    @GetMapping("/test3")
    public Object test3() {
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "dbToEs";
    }
}
