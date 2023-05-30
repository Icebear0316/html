package cn.tedu.boot1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.boot1.mapper")
public class MybatisConfig {

}
