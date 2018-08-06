package com.spring.study.enums;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
public enum ErrorStatusCode {

    SYSTEM_ERROR(1005, "服务器错误"),
    TOKEN_ERROR(1006, "权限不足请重新登录");

    private final int value;
    private final String reasonPhrase;

    ErrorStatusCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String reasonPhrase() {
        return this.reasonPhrase;
    }

    public static ErrorStatusCode statOf(int index) {
        for (ErrorStatusCode statusEnum : values()) {
            if (statusEnum.value() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
