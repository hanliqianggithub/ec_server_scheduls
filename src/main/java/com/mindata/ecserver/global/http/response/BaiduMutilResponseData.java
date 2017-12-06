package com.mindata.ecserver.global.http.response;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/11/29.
 */
public class BaiduMutilResponseData implements ResponseValue {
    /**
     * 成功是0
     */
    private Integer status;
    /**
     * 返回数据总数
     */
    private Integer total;
    /**
     * 返回多个经纬度
     */
    private List<BaiduMutilResponseBean> results;

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Object getData() {
        return results;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<BaiduMutilResponseBean> getResults() {
        return results;
    }

    public void setResults(List<BaiduMutilResponseBean> results) {
        this.results = results;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
