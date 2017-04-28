package com.whc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by whc on 2017/4/27.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()    // 指定哪些url需要被保护，哪些不需要被保护
                .antMatchers("/", "/hello").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()            // 指定用户登录跳转的登录页面
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    /**
     * 在内存中创建一个用户（用户名：user，密码：password，角色：USER）
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password("password").roles("USER");
    }
}
