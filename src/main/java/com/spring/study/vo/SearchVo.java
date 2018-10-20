package com.spring.study.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/9.
 */
@Data
public class SearchVo implements Serializable {

    private String startDate;
    private String endDate;
}
