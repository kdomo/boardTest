package com.domo.boardtest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers(
                "/users/signup/**",
                "/swagger-ui/**",
                "/api/v1/login" // 임시
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()

                // 인증 절차를 생략할 API를 지정
                .authorizeRequests()
                .antMatchers("/user/signup", "/user/login", "/h2-console/**").permitAll()

                // 그 외 API는 인증 절차 수행
                .anyRequest().authenticated().and().build();

    }
}
