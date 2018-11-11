package com.spring.study.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.spring.study.vo.EsLog;
import com.spring.study.mapper.dao.EsLogDao;
import com.spring.study.service.EsLogService;
import com.spring.study.vo.SearchVo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/10.
 */
@Transactional
@Service("EsLogService")
public class EsLogServiceImpl implements EsLogService {

    private final EsLogDao esLogDao;

    @Autowired
    public EsLogServiceImpl(EsLogDao esLogDao) {
        this.esLogDao = esLogDao;
    }

    @Override
    public EsLog saveLog(EsLog esLog) {
        return esLogDao.save(esLog);
    }

    @Override
    public void deleteLog(String id) {
        esLogDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esLogDao.deleteAll();
    }

    @Override
    public Page<EsLog> getLogList(Pageable pageable) {
        return esLogDao.findAll(pageable);
    }

    @Override
    public Page<EsLog> searchLog(String key, SearchVo searchVo, Pageable pageable) {

        if (StrUtil.isBlank(key) && StrUtil.isBlank(searchVo.getStartDate())) {
            return null;
        }

        QueryBuilder qb;
        QueryBuilder qb1 = QueryBuilders.multiMatchQuery(key, "requestUrl", "requestType", "requestParam", "username", "ip", "ipInfo");

        //仅有key
        if (StrUtil.isNotBlank(key) && StrUtil.isBlank(searchVo.getStartDate()) && StrUtil.isBlank(searchVo.getEndDate())) {
            qb = qb1;
        } else if (StrUtil.isBlank(key) && StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            //仅有时间范围
            Long start = DateUtil.parse(searchVo.getStartDate()).getTime();
            Long end = DateUtil.endOfDay(DateUtil.parse(searchVo.getEndDate())).getTime();
            QueryBuilder qb2 = QueryBuilders.rangeQuery("timeMillis").gte(start).lte(end);
            qb = qb2;
        } else {
            //两者都有
            Long start = DateUtil.parse(searchVo.getStartDate()).getTime();
            Long end = DateUtil.endOfDay(DateUtil.parse(searchVo.getEndDate())).getTime();
            QueryBuilder qb2 = QueryBuilders.rangeQuery("timeMillis").gte(start).lte(end);
            qb = QueryBuilders.boolQuery().must(qb1).must(qb2);
        }

        //多字段搜索
        return esLogDao.search(qb, pageable);
    }
}
