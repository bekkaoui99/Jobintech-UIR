package com.hamzabekkaoui.cvcrudapp.configuration;

import com.hamzabekkaoui.cvcrudapp.repositories.JDBCManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class ApplicationConfiguration {

    @Value("${spring.datasource.url}")
    String jdbcUrl;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;


    @Bean
    public JDBCManager jdbcManager() throws SQLException {
        return new JDBCManager(jdbcUrl, username, password);
    }
}
