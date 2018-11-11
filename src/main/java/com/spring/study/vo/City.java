package com.spring.study.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/10.
 */
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
