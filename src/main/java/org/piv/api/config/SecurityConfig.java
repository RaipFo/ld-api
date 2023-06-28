package org.piv.api.config;

import lombok.RequiredArgsConstructor;
import org.piv.api.exception.RestAccessDeniedHandler;
import org.piv.api.exception.RestAuthenticationEntryPoint;
import org.piv.api.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/**/auth/*", "/**/events", "/**/events/*").permitAll()
            .antMatchers("/**/contracts/*").hasAuthority("EVENT_ADMIN")
            .antMatchers("/**/contracts", "/**/contracts/**").hasAuthority("PRINCIPAL")
            .antMatchers("/**/events/*/register").hasAuthority("PARTICIPANT")
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
            .authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
