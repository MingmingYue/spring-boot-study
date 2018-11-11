package com.spring.study.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.spring.study.bean.Response;
import com.spring.study.vo.EsCount;
import com.spring.study.vo.EsInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/20.
 */
@RestController
@Api(description = "Elasticsearch信息接口")
@RequestMapping("/es")
@Transactional
public class EsController {

    @Value("${spring-boot.elasticsearch.nodeClient}")
    private String ES_NODE_CLIENT;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "获取es状态")
    public Response<EsInfo> getAllByPage() {

        String healthUrl = "http://" + ES_NODE_CLIENT + "/_cluster/health";
        String countUrl = "http://" + ES_NODE_CLIENT + "/_count";
        String healthResult = HttpUtil.get(healthUrl);
        String countResult = HttpUtil.get(countUrl);
        if (StrUtil.isBlank(healthResult) || StrUtil.isBlank(countResult)) {
            throw new RuntimeException("连接ES失败，请检查ES运行状态");
        }
        EsInfo esInfo;
        EsCount esCount;
        try {
            esInfo = new Gson().fromJson(healthResult, EsInfo.class);
            esCount = new Gson().fromJson(countResult, EsCount.class);
            esInfo.setCount(esCount.getCount());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取ES信息出错");
        }
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), esInfo);
    }
}
