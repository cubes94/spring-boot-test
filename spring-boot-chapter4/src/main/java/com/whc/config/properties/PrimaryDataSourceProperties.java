package com.whc.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * Created by whc on 2017/4/13.
 */
@ConfigurationProperties(prefix="spring.datasource.primary")
public class PrimaryDataSourceProperties extends Properties {

    private String url;

    private String user;

    private String password;
}
