package com.elk.springbootelk.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zmf
 * @version 1.0
 * @ClassName MyBatisConfig
 * @Description: Mybatis配置类
 * @date 2020/3/9 14:30
 */
@Configuration
@MapperScan("com.elk.springbootelk.mbg.mapper")
public class MyBatisConfig {
}
