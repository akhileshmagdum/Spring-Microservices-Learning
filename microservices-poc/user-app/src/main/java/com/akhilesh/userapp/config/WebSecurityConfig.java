package com.akhilesh.userapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetails userDetails;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/db-user/**","/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable() //To make H2 console work with spring security
                .and()
                .addFilter(getAuthFilter());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationFilter getAuthFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/sign-in"); //Custom login path
        return authenticationFilter;
    }
}
