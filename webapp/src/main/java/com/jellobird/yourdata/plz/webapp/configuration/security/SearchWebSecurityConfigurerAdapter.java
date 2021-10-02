package com.jellobird.yourdata.plz.webapp.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SearchWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("rest").password(BasicAuthEntryPoint.passwordEncoder().encode("rest"))
                .authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/securityNone").permitAll()
                .antMatchers("/api/v1").hasAuthority("ROLE_USER")
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

}