package com.mindata.ecserver.global;

import org.springframework.data.domain.Sort;

/**
 * @author wuweifeng wrote on 2017/11/9.
 */
public interface Constant {

    String ES_INDEX_NAME = "ec-server";
    String ES_TYPE_NAME = "contact";
    String ES_VOCATION_TYPE_NAME = "vocation";

    String ES_TYPE_AREA = "code-area";

    String ES_GEO_INDEX_NAME = "geo";

    String ES_COORDINATE_TYPE_NAME = "coordinate";

    /**
     * 正常态
     */
    int STATE_NORMAL = 0;

    String DOUHAO = ",";

    Sort.Direction DIRECTION = Sort.Direction.DESC;


    Integer CODESIZE_0_50 = 1;
    Integer CODESIZE_51_150 = 2;
    Integer CODESIZE_151_500 = 3;
    Integer CODESIZE_501_2000 = 4;
    Integer CODESIZE_2000 = 5;
    Integer CODESIZE_OTHER = 6;
}
