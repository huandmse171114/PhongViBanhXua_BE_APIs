package com.phongvi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	@Profile(value = "dev")
	SecurityFilterChain productSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests ->
        authorizeRequests
        		.requestMatchers(
        				"/",
        				"/swagger-ui/index.html",
        				"/v3/api-docs/**",
        				"/store/api/v1/auth/sign-in",
        				"/store/api/v1/users/register",
        				"/store/api/v1/**").permitAll()
        		.anyRequest().authenticated());
		http.sessionManagement(
		        session ->
		                session.sessionCreationPolicy(
		                        SessionCreationPolicy.STATELESS)
		);
		http.headers(headers -> headers
		        .frameOptions(frameOptions -> frameOptions
		                .sameOrigin()
		        )
		);
		http.csrf(csrf -> csrf.disable());
		http.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}
}
