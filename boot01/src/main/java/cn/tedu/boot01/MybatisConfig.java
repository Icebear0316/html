package cn.tedu.boot01;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.boot01.mapper")
public class MybatisConfig {

}
