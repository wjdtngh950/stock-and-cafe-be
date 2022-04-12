package com.blog.stockandcafebe.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    private final UserDetailsService userDetailsService;
    
    private final JwtRequestFilter jwtRequestFilter;
    
    private final DataSource dataSource;
    
    public WebSecurityConfig(
            @Lazy DataSource dataSource,
            @Lazy JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            @Lazy UserDetailsService userDetailsService,
            @Lazy JwtRequestFilter jwtRequestFilter
    ) {
        this.dataSource = dataSource;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.jdbcAuthentication()
            .dataSource(dataSource);
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/authenticate", "/api/v1/members")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
