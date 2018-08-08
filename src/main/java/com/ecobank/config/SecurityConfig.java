package com.ecobank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecobank.backend.service.CustomLogoutSuccessHandler;
import com.ecobank.backend.service.UserSecurityService;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private Environment env;

    /** The encryption SALT. */
    private static final String SALT = "fdalkjalk;3jlwf00sfaof";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    /** Public URLs. */
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/app/**",
            "/fonts/**",
            "/js/**",
            "/images/**",
            "/img/**",
            "/assets/**",
            "/"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        

        http
        			.csrf()
        			.disable()
                .authorizeRequests()
                .antMatchers("/file/upload").access("hasRole('ROLE_DH')")
    			.antMatchers("/file/uploaded/files").access("hasRole('ROLE_CSO') || hasRole('ROLE_DPO')")
    			.antMatchers("/file/uploaded/file/**").access("hasRole('ROLE_CSO') || hasRole('ROLE_DPO')")
    			.antMatchers("/file/upload/delete").access("hasRole('ROLE_DPO')")
    			.antMatchers("/file/call-log/delete").access("hasRole('ROLE_DPO')")
    			

                .antMatchers(PUBLIC_MATCHERS).permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/account/dashboard")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());
    }
}
