package com.whc.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.whc.config.properties.PrimaryDataSourceProperties;
import com.whc.config.properties.SecondaryDataSourceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * Created by whc on 2017/3/29.
 */
@Configuration
@EnableConfigurationProperties({ PrimaryDataSourceProperties.class, SecondaryDataSourceProperties.class })
public class DataSourceConfig {

    Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private PrimaryDataSourceProperties primaryDataSourceProperties;
    @Autowired
    private SecondaryDataSourceProperties secondaryDataSourceProperties;

    @Bean(name = "primaryMysqlXADataSource")
    @Primary
    public AtomikosDataSourceBean primaryMysqlXADataSource() {
        LOGGER.info("-----------------CREATE primaryMysqlXADataSource----------------");
        AtomikosDataSourceBean primaryMysqlXADataSource = new AtomikosDataSourceBean();
        primaryMysqlXADataSource.setXaDataSourceClassName(MysqlXADataSource.class.getName());
        primaryMysqlXADataSource.setXaProperties(primaryDataSourceProperties);
        return primaryMysqlXADataSource;
    }

    @Bean(name = "secondaryMysqlXADataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public AtomikosDataSourceBean secondaryMysqlXADataSource() {
        LOGGER.info("-----------------CREATE secondaryMysqlXADataSource----------------");
        AtomikosDataSourceBean secondaryMysqlXADataSource = new AtomikosDataSourceBean();
        secondaryMysqlXADataSource.setXaDataSourceClassName(MysqlXADataSource.class.getName());
        secondaryMysqlXADataSource.setXaProperties(secondaryDataSourceProperties);
        return secondaryMysqlXADataSource;
    }

    @Bean(name = "transactionManager")
    public JtaTransactionManager jtaTransactionManager() {
        LOGGER.info("-----------------CREATE transactionManager----------------");
        JtaTransactionManager transactionManager = new JtaTransactionManager();
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        transactionManager.setTransactionManager(userTransactionManager);
        transactionManager.setUserTransaction(new UserTransactionImp());
        return transactionManager;
    }
}
