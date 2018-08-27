package com.spring.study.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "0女 1男 2保密")
    @Column(length = 1)
    private Integer sex;

    @ApiModelProperty(value = "用户头像")
    @Column(length = 1000)
    private String avatar = "https://s1.ax1x.com/2018/05/19/CcdVQP.png";

    @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
    private Integer type = 0;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
    private Integer status = 0;

    @ApiModelProperty(value = "描述/详情/备注")
    private String description;

    @Transient
    @ApiModelProperty(value = "用户拥有角色")
    private List<Role> roles;

    @Transient
    @ApiModelProperty(value = "用户拥有的权限")
    private List<Permission> permissions;
}
