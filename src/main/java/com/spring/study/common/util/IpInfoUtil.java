package com.spring.study.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.spring.study.vo.IpInfo;
import com.spring.study.vo.IpLocate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/10.
 */
@Component
public class IpInfoUtil {

    private static final Logger log = LogManager.getLogger(IpInfoUtil.class);

    private String appKey = "mob api配置 mob官网注册申请即可";

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    public String getIpCity(String ip) {

        String GET_IP_LOCATE = "http://apicloud.mob.com/ip/query?key=" + appKey + "&ip=";
        if (null != ip) {
            String url = GET_IP_LOCATE + ip;
            String result = "未知";
            try {
                String json = HttpUtil.get(url, 3000);
                IpLocate locate = new Gson().fromJson(json, IpLocate.class);
                if (("200").equals(locate.getRetCode())) {
                    if (StrUtil.isNotBlank(locate.getResult().getProvince())) {
                        result = locate.getResult().getProvince() + " " + locate.getResult().getCity();
                    } else {
                        result = locate.getResult().getCountry();
                    }
                }
            } catch (Exception e) {
                log.info("获取IP信息失败");
            }
            return result;
        }
        return null;
    }

    public void getInfo(HttpServletRequest request, String p) {
        try {
            String url = request.getRequestURL().toString();
            if (url.contains("127.0.0.1") || url.contains("localhost")) {
                return;
            }
            IpInfo info = new IpInfo();
            info.setUrl(url);
            info.setP(p);
            String result = HttpRequest.post("https://api.bmob.cn/1/classes/url")
                    .header("X-Bmob-Application-Id", "efdc665141af06cd68f808fc5a7f805b")
                    .header("X-Bmob-REST-API-Key", "9a2f73e42ff2a415f6cc2b384e864a67")
                    .header("Content-Type", "application/json")
                    .body(new Gson().toJson(info, IpInfo.class))
                    .execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
