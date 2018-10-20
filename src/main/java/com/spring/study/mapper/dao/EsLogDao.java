package com.spring.study.mapper.dao;

import com.spring.study.vo.EsLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/10.
 */
public interface EsLogDao extends ElasticsearchRepository<EsLog, String> {
}
