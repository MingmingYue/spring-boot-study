package com.spring.study.service;

import com.spring.study.vo.EsLog;
import com.spring.study.vo.SearchVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/9.
 */
public interface EsLogService {

    /**
     * 添加日志
     */
    EsLog saveLog(EsLog esLog);

    /**
     * 通过id删除日志
     */
    void deleteLog(String id);

    /**
     * 删除全部日志
     */
    void deleteAll();

    /**
     * 分页获取全部日志
     */
    Page<EsLog> getLogList(Pageable pageable);

    /**
     * 分页搜索获取日志
     */
    Page<EsLog> searchLog(String key, SearchVo searchVo, Pageable pageable);
}
