package com.spring.study.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/27.
 */
@Data
@Entity
@Table(name = "t_role_permission")
@TableName("t_role_permission")
public class RolePermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "权限id")
    private String permissionId;
}
