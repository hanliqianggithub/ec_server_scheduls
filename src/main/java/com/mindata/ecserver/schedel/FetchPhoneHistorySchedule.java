package com.mindata.ecserver.schedel;

import com.mindata.ecserver.global.ZkConstant;
import com.mindata.ecserver.global.util.CommonUtil;
import com.mindata.ecserver.main.service.FetchCompanyPhoneHistoryService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.xiaoleilu.hutool.date.DatePattern.NORM_DATETIME_FORMAT;

/**
 * 每天晚上2点获取昨天的通话历史
 * @author wuweifeng wrote on 2017/11/5.
 */
@Component
public class FetchPhoneHistorySchedule {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private FetchCompanyPhoneHistoryService fetchCompanyPhoneHistoryService;
    @Resource
    private CuratorFramework client;

    /**
     * 注意分布式锁的问题
     */
    @Scheduled(cron = "0 0/20 6 * * ?")
    public void executeFetchPhoneHistoryTask() throws Exception {
        InterProcessMutex interProcessMutex = new InterProcessMutex(client, ZkConstant.PATH_FETCH_PHONEHISTORY);
        //只等1毫秒，目的是不管多少docker，只要有一个执行了就OK了，其他的不需要执行
        if (!interProcessMutex.acquire(1L, TimeUnit.MILLISECONDS)) {
            logger.info("主机名为" + CommonUtil.getHostName() + "没取到锁");
            return;
        }
        logger.info("主机名为" + CommonUtil.getHostName() + "取到锁，开始获取通话历史");
        logger.info("现在时间：" + DateUtil.format(new Date(), NORM_DATETIME_FORMAT));
        logger.info("开始去获取昨天的通话统计信息");
        logger.info("结果是：" + fetchCompanyPhoneHistoryService.fetch());
        interProcessMutex.release();
    }
}
