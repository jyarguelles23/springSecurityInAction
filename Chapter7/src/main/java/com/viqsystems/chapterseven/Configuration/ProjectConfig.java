package com.viqsystems.chapterseven.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    //METHOD WITH AUTHORITIES
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest()//.hasAuthority("WRITE");
                // Permits requests from users with both WRITE and READ authorities
                .hasAnyAuthority("WRITE", "READ");

        //States that the user must have the authority read but not the authority delete
        //String expression = "hasAuthority('read') and !hasAuthority('delete')";
        //.access(expression);

        // Authorizes requests from users with the WRITE authority;
        //.access("hasAuthority('WRITE')")

    }*/

    //METHOD WITH ROLES
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest()
                /*The hasRole() method now specifies the roles for which access to the endpoint is permitted.
                Mind that the ROLE_ prefix does not appear here.*/
                .hasRole("ADMIN");
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var manager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password("12345")
                .authorities("ROLE_ADMIN")
                .build();
        var user2 = User.withUsername("jane")
                .password("12345")
                .authorities("ROLE_MANAGER")
                .build();

        var user3 = User.withUsername("mel")
                .password("12345")
                .roles("ADMIN") //The roles() method specifies the user’s roles.
                .build();
        manager.createUser(user1);
        manager.createUser(user2);
        manager.createUser(user3);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
