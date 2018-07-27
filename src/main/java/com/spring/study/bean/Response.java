package com.spring.study.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/26.
 */
@ApiModel(description = "返回信息")
public class Response<T> {

    @ApiModelProperty("状态响应码")
    private int code;
    @ApiModelProperty("描述")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;

    private Response(String message) {
        this.message = message;
    }

    private Response(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> Response<T> success(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    public static <T> Response success(int code, T data) {
        return new Response<>(code, "", data);
    }

    public static <T> Response<T> success() {
        return success(RequestStatus.REQUEST_OK, true);
    }

    public static <T> Response<T> success(T data) {
        return success(RequestStatus.REQUEST_OK, data);
    }

    public static <T> Response<T> failure(int code, String message) {
        return new Response(code, message, false);
    }

    public static Response failure(String message) {
        return new Response(message);
    }
}
