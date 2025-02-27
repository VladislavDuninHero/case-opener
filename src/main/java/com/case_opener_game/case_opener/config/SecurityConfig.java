package com.case_opener_game.case_opener.config;

import com.case_opener_game.case_opener.constants.Cookies;
import com.case_opener_game.case_opener.constants.Routes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

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
                                .requestMatchers(
                                        Routes.API_LOGIN_ROUTE
                                ).permitAll()
                                .requestMatchers(
                                        HttpMethod.POST, Routes.API_USER_ROUTE
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
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

}
