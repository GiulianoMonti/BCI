package com.bci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.bci.security.JwtAuthenticationEntryPoint;
import com.bci.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthEntryPoint;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthEntryPoint) {
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests(authorize -> authorize.antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/v1/sign_up").permitAll().antMatchers("/v2/api-docs/**")
                        .permitAll().antMatchers("/v3/api-docs/**").permitAll().antMatchers("/swagger-ui/**")
                        .permitAll().antMatchers("/swagger-resources/**").permitAll().antMatchers("/swagger-ui.html")
                        .permitAll().antMatchers("/webjars/**").permitAll().antMatchers("/h2-console/**").permitAll()
                        .antMatchers("/openapi.yaml/**").permitAll()

                        .anyRequest().authenticated());
        return http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class).build();

    }

    @Bean
    JwtAuthenticationFilter authTokenFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}