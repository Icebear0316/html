package cn.tedu.baking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 跨域访问：只要协议、IP或主机名、端口以上3者中的任何1处不同，
// 客户端向服务器提交的请求就会被视为“跨域”的请求
// 默认情况下，所有的跨域请求都是被服务器端禁止的
// 此类问题的关键字是【CORS】
// -------------------------------------------
// 在基于Spring MVC的服务器端，可以自定义配置类，
// 实现WebMvcConfigurer接口，重写addCorsMappings()方法配置允许的跨域访问
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*") // .allowedOrigins("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
