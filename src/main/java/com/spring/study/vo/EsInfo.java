package com.spring.study.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/20.
 */
@Data
public class EsInfo implements Serializable {

    private String cluster_name;

    private String status;

    private String number_of_nodes;

    private Integer count;
}
