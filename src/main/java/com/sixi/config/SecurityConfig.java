package com.sixi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    public SecurityConfig(){
        System.out.println("SecurityConfig");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/druid/*");
        http.authorizeRequests()
                .antMatchers("/homepage/book").permitAll()
                .antMatchers("/user1/**").hasRole("USER")//.hasAuthority("USER")
                .and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("suger").password("1").roles("USER").and()
                .withUser("admin").password("password").roles("USER","ADMIN");
    }
}
