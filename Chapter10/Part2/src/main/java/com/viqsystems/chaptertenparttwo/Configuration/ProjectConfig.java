package com.viqsystems.chaptertenparttwo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    /*The HTTP session is stateful and reduces the scalability of the application.*/

    @Bean
    public CsrfTokenRepository customTokenRepository() {
        return new CustomCsrfTokenRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*The parameter of the lambda expression is a CsrfConfigurer.
         By calling its methods, you can configure CSRF protection in various ways.*/
        http.csrf(c -> {
            c.csrfTokenRepository(customTokenRepository());
            c.ignoringAntMatchers("/ciao");});
              http.authorizeRequests()
                      .anyRequest().permitAll();

     /*   The next code snippet shows how to use the ignoringRequestMatchers() method
        with an MvcRequestMatcher instead of using ignoringAntMatchers():
        HandlerMappingIntrospector i = new HandlerMappingIntrospector();
        MvcRequestMatcher r = new MvcRequestMatcher(i, "/ciao");
        c.ignoringRequestMatchers(r);

        Or, you can similarly use a regex matcher as in the next code snippet:
        String pattern = ".*[0-9].*";
        String httpMethod = HttpMethod.POST.name();
        RegexRequestMatcher r = new RegexRequestMatcher(pattern, httpMethod);
        c.ignoringRequestMatchers(r);
        */
    }

    /* CORS EXAMPLE IN CONFIGURATION FILE

    @Override
        protected void configure(HttpSecurity http) throws Exception {
        http.cors(c -> {
        CorsConfigurationSource source = request -> {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(
        List.of("example.com", "example.org"));
        config.setAllowedMethods(
        List.of("GET", "POST", "PUT", "DELETE"));
        return config;
        };
        c.configurationSource(source);
        });
        http.csrf().disable();
        http.authorizeRequests()
        .anyRequest().permitAll();
        }
    * */

    /*
    *  A cross-site request forgery (CSRF) is a type of attack where the user is tricked
        into accessing a page containing a forgery script. This script can impersonate a
        user logged into an application and execute actions on their behalf.
         CSRF protection is by default enabled in Spring Security.
         The entry point of CSRF protection logic in the Spring Security architecture is
        an HTTP filter.
         Cross-over resource sharing (CORS) refers to the situation in which a web
        application hosted on a specific domain tries to access content from another
        domain. By default, the browser doesn’t allow this to happen. CORS configuration enables you to allow a part of your resources to be called from a different
        domain in a web application run in the browser.
         You can configure CORS both for an endpoint using the @CrossOrigin annotation or centralized in the configuration class using the cors() method of the
        HttpSecurity object
    * */
}
