package cn.tedu.baking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法授权的检测
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //配置密码加密的方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        //NoOpPasswordEncoder.getInstance()获取一个无加密的实例
        //return NoOpPasswordEncoder.getInstance();
        //返回此加密的编码器之后,用户输入的密码会通过此编码器加密之后再和数据库里面的密码进行比较
        return new BCryptPasswordEncoder();
    }

    @Bean  //添加此注解的目的是为了在Controller中自动装配
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //当访问需要登录认证才能访问的资源时, 没有登录的时跳转到登录页面
            http.formLogin().loginPage("/login.html");//弹出登录页面
        //设置白名单(不需要登录即可访问的资源)
        String[] urls = {"/admin.html","/personal.html"};
        http.authorizeHttpRequests()//对请求进行授权
                .mvcMatchers(urls) //  需要通过登录认证
                .authenticated()   //直接放行,  即不需要登录也可以访问
                .anyRequest()   //其它任意请求
                .permitAll();//匹配某些路径

        //关闭跨域攻击防御策略  否则所有post请求将失效
        http.csrf().disable();
    }
}
