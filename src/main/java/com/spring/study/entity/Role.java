package com.spring.study.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/27.
 */
@Data
@Entity
@Table(name = "t_role")
@TableName("t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名 以ROLE_开头")
    private String name;

    @ApiModelProperty(value = "是否为注册默认角色")
    private Boolean defaultRole;

    @Transient
    @TableField(exist = false)
    @ApiModelProperty(value = "拥有权限")
    private List<Permission> permissions;
}
