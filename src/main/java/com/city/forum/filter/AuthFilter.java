package com.city.forum.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * cityforum
 *  授权过滤器
 * @author : chenDW
 * @date : 2021-09-13 19:04
 **/
@Slf4j
@Component
public class AuthFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private SecurityMetadataSource securityMetadataSource;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("---AuthFilter---");

        FilterInvocation fi = new FilterInvocation(servletRequest,servletResponse,filterChain);
        // 这里调用了父类的AbstractSecurityInterceptor的方法,也就是调用了accessDecisionManager
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(),fi.getResponse());
        }finally {
            super.afterInvocation(token,null);
        }

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        // 将我们自定义的SecurityMetadataSource给返回
        return this.securityMetadataSource;
    }

    @Override
    @Autowired
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }
}
