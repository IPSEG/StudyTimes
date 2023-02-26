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
        hikariConfig.setJdbcUrl("jdbc:postgresql://db:5432/studytimes");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("vhtmrmfptm1!");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }
}
