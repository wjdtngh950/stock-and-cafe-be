package com.blog.stockandcafebe.config;

import com.blog.stockandcafebe.security.filter.ApiCheckFilter;
import com.blog.stockandcafebe.security.filter.ApiLoginFilter;
import com.blog.stockandcafebe.security.handler.ApiLoginFailHandler;
import com.blog.stockandcafebe.security.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/articles/**");
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
        apiLoginFilter.setAuthenticationManager(authenticationManager());
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
        return apiLoginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/auth/all/**").permitAll();
        http.authorizeRequests().antMatchers("/auth/member/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/auth/manager/**").hasRole("MANAGER");
        http.authorizeRequests().antMatchers("/auth/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/members/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/articles/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/replies/**").hasRole("USER");
        http.csrf().disable();
        http.logout();
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
