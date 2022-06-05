package com.piisw.backend.configuration.security;

import com.piisw.backend.security.AppUserDetailsService;
import com.piisw.backend.security.CustomAuthenticationEntryPoint;
import com.piisw.backend.security.CustomAuthenticationFailureHandler;
import com.piisw.backend.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] APP_AUTHENTICATION_WHITELIST = {
            "/api/version",
            "/api/translations/**",
            "/h2-console/**",
            "/api/tokens/**",
            "/api/passengers"
    };

    private static final String[] SWAGGER_AUTHENTICATION_WHITELIST = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html/**",
            "/v3/api-docs/**",
            "/webjars/**"
    };

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    HttpStatusReturningLogoutSuccessHandler httpStatusReturningLogoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors()
                .and()
                .formLogin()
                .failureHandler(customAuthenticationFailureHandler)
                .successHandler(customAuthenticationSuccessHandler)
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(httpStatusReturningLogoutSuccessHandler())
                .and()
                .authorizeRequests()
                .antMatchers(APP_AUTHENTICATION_WHITELIST).permitAll()
                .antMatchers(SWAGGER_AUTHENTICATION_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
}