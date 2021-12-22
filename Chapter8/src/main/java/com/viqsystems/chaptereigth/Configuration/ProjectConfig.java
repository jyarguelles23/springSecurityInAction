package com.viqsystems.chaptereigth.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        When you use matchers to refer to requests, the order of the rules
        should be from particular to general. This is why the anyRequest() method
        cannot be called before a more specific matcher method like mvcMatchers()
        */
        http.httpBasic();

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET,"/a").authenticated()
                .mvcMatchers(HttpMethod.POST,"/a").permitAll()
                /*The regex refers to strings of any length,containing any digit.*/
                .mvcMatchers("/product/{code:^[0-9]*$}").permitAll()
                //testing regexMatchers
                .regexMatchers(".*/(us|uk|ca)+/(en|fr).*").authenticated()
                .mvcMatchers("/hello").hasRole("ADMIN")
                .mvcMatchers("/ciao").hasRole("MANAGER")
                .anyRequest().denyAll();
                //.hasAuthority("premium");
  
         /*The permitAll() method states that all other requests are allowed without authentication*/
        //.anyRequest().permitAll();

        /*All other requests are accessible only by authenticated users*/
        //.anyRequest().authenticated();



        /*Disables CSRF to enable a call to the /a path using the HTTP POST method*/
        http.csrf().disable();


    }


    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();
        var user2 = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();
        manager.createUser(user1);
        manager.createUser(user2);
        return manager;

        /*
        * var u2 = User.withUsername("jane")
            .password("12345")
            .authorities("read", "premium")
            .build();
        * */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
