package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConfigurationFile {
    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource= new DriverManagerDataSource();
         dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
         dataSource.setUrl("jdbc:mysql://localhost:3307/spring_jdbc_db");
         dataSource.setUsername("root");
         dataSource.setPassword("1999SPMKPC@mysql");
         return dataSource;
    }
    @Bean
    public JdbcTemplate  myJdbcTemplate(){
        JdbcTemplate template= new JdbcTemplate();
        template.setDataSource(dataSource());
        return  template;
    }
    @Bean
    public NamedParameterJdbcTemplate template(){
        return new NamedParameterJdbcTemplate(dataSource());
    }
}
