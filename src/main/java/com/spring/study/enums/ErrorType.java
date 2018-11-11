package com.spring.study.enums;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
public enum ErrorType {

    SYSTEM_ERROR(1005, "服务器错误"),
    TOKEN_ERROR(1006, "权限不足请重新登录"),
    REGISTER_ERROR(1007, "注册失败"),
    NOT_LOGIN_IN(1008, "未登录");


    private final int value;
    private final String reasonPhrase;

    ErrorType(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String reasonPhrase() {
        return this.reasonPhrase;
    }

    public static ErrorType statOf(int index) {
        for (ErrorType statusEnum : values()) {
            if (statusEnum.value() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
