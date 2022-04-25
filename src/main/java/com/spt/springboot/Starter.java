package com.spt.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // 开启缓存
@MapperScan("com.spt.springboot.dao") // 扫描指定包下的接口类
public class Starter {

    public static void main(String[] args) {
        SpringApplication .run(Starter.class);
    }
}
