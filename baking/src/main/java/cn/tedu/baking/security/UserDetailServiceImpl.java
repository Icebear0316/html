package cn.tedu.baking.security;



import cn.tedu.baking.mapper.UserMapper;
import cn.tedu.baking.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired(required = false)
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过用户输入的用户名从数据库中查询信息
        UserVO userVO = mapper.selectByUserName(username);
        if (userVO==null){
            return null;//代表用户名不存在,此时会抛出异常
        }
//        UserDetails ud = User.builder()
//                .username(username).password(userVO.getPassword())
//                .disabled(false) //是否禁用
//                .accountLocked(false) //是否锁定
//                .accountExpired(false) //登录是否过期
//                .credentialsExpired(false) //登录凭证是否过期
//                .authorities("权限")   //授权, 授予当前登录用户有哪些权限
//                .build();
        //模拟libai为管理员 其它是用户
        String role = username.equals("libai")?"ADMIN":"USER";
      // 如果用户输入的密码和数据库中查询到的密码不一致 则会抛出异常
        List<GrantedAuthority> list =
                AuthorityUtils.createAuthorityList(role);
        //创建自定义的UserDetails 并把后期需要用到的id和昵称保存到里面
        CustomUserDetails cud = new CustomUserDetails(userVO.getIsAdmin(),
                userVO.getId(),userVO.getNickName(),userVO.getImgUrl(),
                username, userVO.getPassword(),list);
        return cud;
    }
}
