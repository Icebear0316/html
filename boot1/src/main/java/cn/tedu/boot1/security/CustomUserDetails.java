package cn.tedu.boot1.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

//因为父类中有构造方法 此时添加@Data会让当前类添加构造方法,此方法可能会和父类的冲突所以报错
@Getter
public class CustomUserDetails extends User {
    private Integer id;
    private String nickname;

    public CustomUserDetails(Integer id, String nickname, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.nickname = nickname;
    }
}
