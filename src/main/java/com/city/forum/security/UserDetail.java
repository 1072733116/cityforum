package com.city.forum.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * cityforum
 *  自定义用户对象
 * @author : chenDW
 * @date : 2021-09-12 23:45
 **/
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserDetail extends User {

    /**
     * 用户实体对象
     */
    private com.city.forum.model.entity.User user;

    public UserDetail(com.city.forum.model.entity.User user, Collection<? extends GrantedAuthority> authorities){
        //调用父类的构造方法，初始化用户名、密码、权限
        super(user.getUserAccount(),user.getUserPassword(),authorities);
        this.user = user;

    }
}
