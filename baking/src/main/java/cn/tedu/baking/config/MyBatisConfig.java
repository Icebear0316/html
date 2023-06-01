package cn.tedu.baking.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.baking.mapper")
public class MyBatisConfig {
}
