package com.shuyan.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/index").permitAll()
                        .requestMatchers("login.html").permitAll()
                        .requestMatchers("/hello").authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login") //specify login request url
//                      .successForwardUrl("/index")
//                      .defaultSuccessUrl("/index")
                        .successHandler(new MyAuthenticationSuccessHandler())
//                      .failureForwardUrl("/login.html")//forward
//                      .failureUrl("/login.html")//redirect
                        .failureHandler(new MyAuthenticationFailureHandler())
                )
                .csrf((csrf) -> csrf.
                        disable());
        return http.build();
    }


}
