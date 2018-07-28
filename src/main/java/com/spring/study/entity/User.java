package com.spring.study.entity;

import com.spring.study.vo.UserVo;
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
public class User {

    private int id;
    private String mobile;
    private String password;
    private String username;
    private String email;
    private String desc;
    private long createAt;
    private long updateAt;

    public UserVo toVo() {
        return UserVo.builder()
                .id(this.id)
                .mobile(this.mobile)
                .username(this.username == null ? "" : this.username)
                .email(this.email == null ? "" : this.email)
                .desc(this.desc == null ? "" : this.desc)
                .createAt(this.createAt)
                .updateAt(this.updateAt)
                .build();
    }
}
