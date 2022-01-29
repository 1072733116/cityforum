package com.city.forum.util;

import com.city.forum.model.entity.User;
import com.city.forum.security.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * cityforum
 * 获取当前用户
 *
 * @author : chenDW
 * @date : 2021-09-21 23:32
 **/
@Component
public class UserUtil {

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail principal = (UserDetail) authentication.getPrincipal();
        User user = principal.getUser();
        return user;
    }
}
