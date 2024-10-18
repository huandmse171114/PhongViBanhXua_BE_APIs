package com.phongvi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.phongvi.auth.AuthEntryPointJwt;
import com.phongvi.auth.AuthTokenFilter;
import com.phongvi.user.UserRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    UserRepository repository;
	
	@Bean
    AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
	
	@Autowired
    private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	@Profile(value = "dev")
	SecurityFilterChain productSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests ->
        authorizeRequests
        		.requestMatchers(
        				"/",
        				"/swagger-ui/**",
        				"/v3/api-docs/**",
        				"/store/api/v1/auth/sign-in",
        				"/store/api/v1/users/register",
        				"/store/api/v1/**").permitAll()
        		.anyRequest().permitAll());
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
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
		http.csrf(csrf -> csrf.disable());
//		http.httpBasic(Customizer.withDefaults());
		http.addFilterBefore(authenticationJwtTokenFilter(),
		        UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    UserDetailsService userDetailsService() {
      return username -> repository.findByUsername(username)
    		  .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
    
    @Bean
    AuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
