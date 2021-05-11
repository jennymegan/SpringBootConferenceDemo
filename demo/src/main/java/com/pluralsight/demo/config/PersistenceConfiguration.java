package com.pluralsight.demo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.zip.DataFormatException;

@Configuration
//you can override system config here
public class PersistenceConfiguration {

//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.url(" you db url here");
//        System.out.println("There are custom settings being used in PersistenceConfig");
//        return builder.build();
//    }

}
