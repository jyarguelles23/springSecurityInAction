package com.viqsystems.chapterfive.Configuration;


import com.viqsystems.chapterfive.AuxClasses.CustomAuthenticationFailureHandler;
import com.viqsystems.chapterfive.AuxClasses.CustomAuthenticationSuccessHandler;
import com.viqsystems.chapterfive.AuxClasses.CustomEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      /*  http.httpBasic(c -> {
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });*/
        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                /*both login allowed*/
                .and()
                .httpBasic();
                /*if login true send to that url always by default
                .defaultSuccessUrl("/home", true);*/
        http.authorizeRequests().anyRequest().authenticated();
    }
}
