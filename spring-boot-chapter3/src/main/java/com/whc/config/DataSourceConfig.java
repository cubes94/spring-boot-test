package com.whc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by whc on 2017/3/29.
 */
@Configuration
public class DataSourceConfig {

    Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        LOGGER.info("-----------------CREATE primaryDataSource----------------");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        LOGGER.info("-----------------CREATE secondaryDataSource----------------");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primaryDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryDataSourceTransactionManager() {
        LOGGER.info("-----------------CREATE primaryDataSourceTransactionManager----------------");
        return new DataSourceTransactionManager(primaryDataSource());
    }

    @Bean(name = "secondaryDataSourceTransactionManager")
    public DataSourceTransactionManager secondaryDataSourceTransactionManager() {
        LOGGER.info("-----------------CREATE secondaryDataSourceTransactionManager----------------");
        return new DataSourceTransactionManager(secondaryDataSource());
    }
}
