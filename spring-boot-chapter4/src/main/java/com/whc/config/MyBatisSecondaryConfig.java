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

import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages = "com.whc.dao.mapper.secondary", sqlSessionFactoryRef = "secondarySqlSessionFactory")
public class MyBatisSecondaryConfig {

    Logger LOGGER = LoggerFactory.getLogger(MyBatisPrimaryConfig.class);

    @Autowired
    @Qualifier("secondaryMysqlXADataSource")
    private DataSource secondaryMysqlXADataSource;

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory() throws Exception {
        LOGGER.info("-----------------CREATE secondarySqlSessionFactory----------------");
        SqlSessionFactoryBean sb = new SqlSessionFactoryBean();
        sb.setDataSource(secondaryMysqlXADataSource);
        return sb.getObject();
    }
}