package com.mindata.ecserver.main.controller;

import com.mindata.ecserver.main.service.CompanyCoordinateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author hanliqiang wrote on 2017/11/27
 */
@RestController
@RequestMapping("/coordinate")
public class CoordinateController {
    @Resource
    private CompanyCoordinateService companyCoordinateService;

    /**
     * 根据地址获取坐标
     *
     * @param address 地址
     * @return 结果
     * @throws IOException 异常
     */
    @GetMapping("/address")
    public Object getCoordinateByAddress(String address) throws IOException {
        return companyCoordinateService.findCoordinateByAddress(address);
    }

    /**
     * 根据公司获取坐标
     *
     * @param companyName 公司名称
     * @param city        城市
     * @return 结果
     * @throws IOException 异常
     */
    @GetMapping("/company")
    public Object getCoordinateByCompany(String companyName, String city) throws IOException {
        return companyCoordinateService.findCoordinateByCompany(companyName, city);
    }

    /**
     * 更新id范围内的数据
     *
     * @param beginId 开始id
     * @param endId   结束id
     * @return 结果
     * @throws IOException 异常
     */
    @PutMapping("/idBetween")
    public Object updateIdBetweenCoordinate(Long beginId, Long endId, Boolean force) throws IOException {
        companyCoordinateService.partInsertIdBetween(beginId, endId, force);
        return "更新完毕";
    }

    /**
     * 更新一段时间范围内的数据
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @return 结果
     * @throws IOException 异常
     */
    @PutMapping("/dateBetween")
    public Object updateDateBetweenCoordinate(String begin, String end, Boolean force) throws IOException {
        companyCoordinateService.partInsertDateBetween(begin, end, force);
        return "更新完毕";
    }
}
