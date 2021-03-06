package com.mindata.ecserver.global.http.service;

import com.mindata.ecserver.global.http.response.GaodeMultipleResponseData;
import com.mindata.ecserver.global.http.response.GaodeResponseData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author hanliqiang wrote on 2017/11/27
 */
public interface GaodeCoordinateService {

    /**
     * 根据地址去高德查经纬度
     *
     * @param address 地址
     * @param output  输出格式
     * @param key     高德key
     * @return 结果
     */
    @GET("v3/geocode/geo")
    Call<GaodeResponseData> getCoordinateByAddress(@Query("address") String address, @Query("output") String output,
                                                   @Query("key") String key);

    /**
     * @param keywords  公司或地址
     * @param city      城市
     * @param offset    pageSize
     * @param page      page
     * @param cityLimit true 返回区域内的数据
     * @param output    输出格式
     * @param key       key
     * @return 结果
     */
    @GET("v3/place/text")
    Call<GaodeMultipleResponseData> getCoordinateByParameter(@Query("keywords") String keywords, @Query("city") String city, @Query("offset") Integer offset, @Query("page") Integer page,
                                                             @Query("citylimit") boolean cityLimit,
                                                             @Query("output") String output,
                                                             @Query("key") String key);


}
