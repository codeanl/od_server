package com.example.deded;

import com.example.deded.config.WebConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@MapperScan("com.example.deded.mapper")
@SpringBootApplication
@Import(WebConfig.class)
public class DededApplication {
    public static void main(String[] args) {
        SpringApplication.run(DededApplication.class, args);
    }
}
