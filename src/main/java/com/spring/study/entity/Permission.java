package com.spring.study.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/30.
 */
@Data
@Builder
public class Permission {

    private int id;
    private String mobile;
}
