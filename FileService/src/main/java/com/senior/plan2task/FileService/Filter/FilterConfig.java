package com.senior.plan2task.FileService.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class FilterConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.GET , "/", "/file/{planId}/{fileName}", "/file/{planId}/{taskId}/{fileName}").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(
                    new JWTGenericFilterBean(),
                    UsernamePasswordAuthenticationFilter.class
            );
    }
}