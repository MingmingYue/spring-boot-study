package com.spring.study.bean;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Data;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/26.
 */
@Data
@Builder
public class CheckResult {

    private int errCode;
    private boolean success;
    private Claims claims;
}
