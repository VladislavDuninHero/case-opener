package com.case_opener_game.case_opener.config;

import com.case_opener_game.case_opener.constants.Cookies;
import com.case_opener_game.case_opener.constants.Routes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        "/css/**",
                                        "/js/**",
                                        "/images/**"
                                ).permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin(login -> login
                        .loginPage(Routes.LOGIN_ROUTE)
                        .defaultSuccessUrl(Routes.HOME_ROUTE)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl(Routes.LOGOUT_ROUTE)
                        .logoutSuccessUrl(Routes.LOGIN_ROUTE)
                        .deleteCookies(Cookies.JSESSIONID_COOKIE)
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
