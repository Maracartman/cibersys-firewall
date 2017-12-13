package com.cibersys.firewall.Configuration;


import com.cibersys.firewall.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Created by AKDESK04 on 3/28/2017.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityService securityService;

    @Bean
    public SecurityService securityService() {
        return this.securityService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .headers()
                .frameOptions()
                .disable().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.PATCH,"/**").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll();
        final CorsConfiguration config = new CorsConfiguration();
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        httpSecurity.addFilterBefore(firebaseFilterBean(), UsernamePasswordAuthenticationFilter.class).authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
        httpSecurity.cors();
        httpSecurity.headers().cacheControl();
    }
    @Bean
    public AppFilter firebaseFilterBean() throws Exception {
        return new AppFilter();
    }
}
