package com.spring.study.service;

import com.spring.study.vo.Log;
import com.spring.study.vo.SearchVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 日志接口
 *
 * @author: ZhouMingming
 * @data: Create on 2018/10/9.
 */
public interface LogService extends BaseService<Log, String> {

    /**
     * 日志搜索
     */
    Page<Log> searchLog(String key, SearchVo searchVo, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
