package com.spring.study.bean;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/6/27.
 */
public class RequestStatus {

    public static final int REQUEST_OK = 200;//（成功）表示服务器成功处理了请求的状态代码。
    public static final int BAD_REQUEST_ERROR = 400; //（错误请求） 服务器不理解请求的语法
    public static final int UNAUTHORIZED_ERROR = 401; //（未授权） 请求要求身份验证。
    public static final int FORBIDDEN_ERROR = 403;//（禁止） 服务器拒绝请求。
    public static final int FILE_NOT_FOUND_ERROR = 404; //（未找到） 服务器找不到请求的网页。
    public static final int REQUEST_TIME_OUT_ERROR = 408; //（请求超时） 服务器等候请求时发生超时。
    public static final int INTERNAL_SERVER_ERROR = 500; //（服务器内部错误） 服务器遇到错误，无法完成请求。
    public static final int SERVICE_UNAVAILABLE_ERROR = 503;//（服务不可用） 服务器目前无法使用
}
