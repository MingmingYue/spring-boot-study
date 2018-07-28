package com.spring.study.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "运营人员信息")
public class UserVo {

    @ApiModelProperty("运营人员id")
    private int id;
    @ApiModelProperty("运营人员账号")
    private String mobile;
    @ApiModelProperty("名字")
    private String username;
    @ApiModelProperty("email")
    private String email;
    @ApiModelProperty("运营人员描述")
    private String desc;
    @ApiModelProperty("令牌")
    private String token;
    @ApiModelProperty("创建时间")
    private long createAt;
    @ApiModelProperty("更新时间")
    private long updateAt;
}