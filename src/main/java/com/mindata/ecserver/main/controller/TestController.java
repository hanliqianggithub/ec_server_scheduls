package com.mindata.ecserver.main.controller;

import com.mindata.ecserver.main.BaseData;
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
        esContactService.dbToEs();
        return "dbToEs";
    }
}