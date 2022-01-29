package com.city.forum.filter;

import com.city.forum.service.impl.UserServiceImpl;
import com.city.forum.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cityforum
 * 登录过滤器
 * @author : chenDW
 * @date : 2021-09-13 13:32
 **/
@Slf4j
@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("---进入LoginFilter---");
        //从请求头中获取token字符串解析
        Claims claims = jwtUtil.parse(request.getHeader("Authorization"));

        if (claims != null){
            String userAccount = claims.getSubject();
            UserDetails currentUser = userService.loadUserByUsername(userAccount);
            Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
            //将认证信息保存到上下文对象
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request,response);
    }
}
