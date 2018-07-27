package com.spring.study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Configuration
@EnableTransactionManagement
public class DruidAutoConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    @Value("${spring.datasource.url}")
    private String myUrl;

    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource writeDataSource() {
        System.out.println(myUrl);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertyResolver.getProperty("url"));
        dataSource.setUsername(propertyResolver.getProperty("username"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        dataSource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        dataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize")));
        dataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("maxActive")));
        dataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("minIdle")));
        dataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("maxWait")));
        dataSource.setUseUnfairLock(Boolean.valueOf(propertyResolver.getProperty("useUnfairLock")));
        dataSource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
        dataSource.setTestOnBorrow(Boolean.valueOf(propertyResolver.getProperty("testOnBorrow")));
        dataSource.setTestOnReturn(Boolean.valueOf(propertyResolver.getProperty("testOnReturn")));
        dataSource.setTestWhileIdle(Boolean.valueOf(propertyResolver.getProperty("testWhileIdle")));
        dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
        try {
            dataSource.setFilters(propertyResolver.getProperty("filters"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

}
