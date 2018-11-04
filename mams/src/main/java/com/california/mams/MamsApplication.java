package com.california.mams;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ffn on 21/10/18.
 */
@EnableScheduling
@EnableTransactionManagement
@EnableConfigurationProperties
@MapperScan("com.california.mams.orm.dao")
@SpringBootApplication(scanBasePackages = {"com.california.mams"})
public class MamsApplication implements CommandLineRunner {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(MamsApplication.class);

    /**
     * Springboot应用程序入口
     */
    public static void main(String[] args) {
        act = SpringApplication.run(MamsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Spring初始化over.");
    }
}
