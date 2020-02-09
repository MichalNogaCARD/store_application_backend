package com.app.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class CustomWebSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public CustomWebSecurity(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .antMatchers("/mange/**", "/webjars/**", "/static/**", "/").permitAll()
                .antMatchers("/users/**").hasAnyRole("USER", "ADMIN", "SUPER") // TODO: 07.02.2020
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPER")
                .antMatchers("/super/**").hasAnyRole("SUPER")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/security/login").permitAll()
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/security/failed")

                .and()
                .logout().permitAll()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .logoutSuccessUrl("/security/loggedOut")

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse,
                               AccessDeniedException e) throws IOException, ServletException {
                httpServletResponse.sendRedirect("/accessDenied");
            }
        };
    }
}