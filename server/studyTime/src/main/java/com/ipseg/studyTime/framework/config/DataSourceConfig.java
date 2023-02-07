package com.ipseg.studyTime.framework.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/task");
        hikariConfig.setUsername("yangseungbin");
        hikariConfig.setPassword("yang123");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }
}
