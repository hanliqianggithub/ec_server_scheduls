package com.mindata.ecserver.schedel;

import com.mindata.ecserver.global.ZkConstant;
import com.mindata.ecserver.global.annotation.EnableZookeeperLockAnnotation;
import com.mindata.ecserver.main.manager.ContactManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author HanLiqiang
 * @create 2018-08-13 上午10:33
 **/
@Component
public class FetchContactSchedule {
    @Resource
    private ContactManager contactManager;


    /**
     * 注意分布式锁的问题
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @EnableZookeeperLockAnnotation(nodeName = ZkConstant.PATH_FETCH_CONTACT)
    public void executeDealCustomerOperationTask() {
        contactManager.fetch58Contact();
    }

}

    

