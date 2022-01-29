package com.city.forum.config;

import com.city.forum.filter.AuthFilter;
import com.city.forum.filter.LoginFilter;
import com.city.forum.security.MyDeniedHandler;
import com.city.forum.security.MyEntryPoint;
import com.city.forum.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * cityforum
 * 自定义安全配置
 *
 * @author : chenDW
 * @date : 2021-09-12 17:15
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userDetailService;

    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private AuthFilter authFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf和frameOptions，如果不关闭会影响前端请求接口
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //开启跨越以便前端调用接口
        http.cors();

        // 这是配置的关键，决定哪些接口开启防护，哪些接口绕过防护
        http.authorizeRequests()
                // 注意这里，是允许前端跨域联调的一个必要配置
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                //指定某些接口不需要通过验证即可访问
                .antMatchers("/api/login", "/api/register","/api/test","/api/getAllPosts","/api/forgetPassword").permitAll()
                .antMatchers("/files/**").permitAll()
                .antMatchers("/static/**").permitAll()
                //其它接口需要认证才能访问
                .antMatchers("/api/**").authenticated()
                // 指定认证错误处理器
                .and().exceptionHandling().authenticationEntryPoint(new MyEntryPoint()).accessDeniedHandler(new MyDeniedHandler());

        //禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 将自定义的认证过滤器替换掉默认的认证过滤器
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authFilter, FilterSecurityInterceptor.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 指定UserDetailService和加密器
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
