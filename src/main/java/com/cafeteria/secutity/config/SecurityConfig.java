package com.cafeteria.secutity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain decurityFilterChain(
			HttpSecurity http
			)throws Exception{
		
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
							"/api/roles/**",
							"/api/users/**",
							"/api/auth/**"
							)
					.permitAll()
					.anyRequest()
					.authenticated()
					)
			.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
}
