package com.whc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages = "com.whc.dao.mapper.primary", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class MyBatisPrimaryConfig {

    Logger LOGGER = LoggerFactory.getLogger(MyBatisPrimaryConfig.class);

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory() throws Exception {
        LOGGER.info("-----------------CREATE primarySqlSessionFactory----------------");
        SqlSessionFactoryBean sb = new SqlSessionFactoryBean();
        sb.setDataSource(primaryDataSource);
        return sb.getObject();
    }


}