package com.example.Homework.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DatabaseProperties dbProperties;

    @Bean
    @Profile("dev")
    @ConditionalOnExpression("'${spring.datasource.url}'.contains('devdb')")
    public DataSource devDataSource() {
        System.out.println("Connecting to DEV Database: " + dbProperties.getUrl());
        return new DriverManagerDataSource(dbProperties.getUrl(), dbProperties.getUsername(), dbProperties.getPassword());
    }

    @Bean
    @Profile("prod")
    @ConditionalOnExpression("'${spring.datasource.url}'.contains('proddb')")
    public DataSource prodDataSource() {
        System.out.println("Connecting to PROD Database: " + dbProperties.getUrl());
        return new DriverManagerDataSource(dbProperties.getUrl(), dbProperties.getUsername(), dbProperties.getPassword());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
