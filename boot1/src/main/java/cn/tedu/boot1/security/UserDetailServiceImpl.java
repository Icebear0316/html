package cn.tedu.boot1.security;


import cn.tedu.boot1.mapper.UserMapper;
import cn.tedu.boot1.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired(required = false)
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = mapper.selectByUsername(username);
        if(userVO==null){
            return null;//代表用户名不存在,此时会抛出异常
        }

        UserDetails ud = User.builder()
                .username("root").password("123456")
                .disabled(false) //是否禁用
                .accountLocked(false) //是否锁定
                .accountExpired(false) //登录是否过期
                .credentialsExpired(false) //登录凭证是否过期
                .authorities("权限")   //授权, 授予当前登录用户有哪些权限
                .build();
        //如果用户输入的用户名不是root 则代表用户名不存在
        return ud;
    }
}
