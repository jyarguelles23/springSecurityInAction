package com.viqsystems.chapternine.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private StaticKeyAuthenticationFilter filter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new RequestValidationFilter(),
                BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthenticationLoggingFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAt(filter,BasicAuthenticationFilter.class)/*Adds the filter at the position of the basic authentication filter in the filter chain*/
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
