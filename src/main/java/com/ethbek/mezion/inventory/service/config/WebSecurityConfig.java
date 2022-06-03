package com.ethbek.mezion.inventory.service.config;

import com.ethbek.mezion.inventory.service.permission.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    TokenAuthenticationFilter tokenAuthenticationFilter;

    public WebSecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/swagger-ui/**", "/uptime","/v2/**", "/v3/**" ,"/api/v1/download/{object}");
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
    	/* Default configuration */
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable() //NOSONAR CoreLogic sits behind a firewall
                .authorizeRequests()
                .anyRequest()
                .authenticated();
        httpSecurity.addFilterBefore(tokenAuthenticationFilter, AnonymousAuthenticationFilter.class);
    }
} 
