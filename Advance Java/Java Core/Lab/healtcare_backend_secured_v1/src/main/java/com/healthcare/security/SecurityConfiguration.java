package com.healthcare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// to declare spring configuration class - to be able to add spring beans (@Bean)
@Configuration
@EnableWebSecurity // to enable Spring security support
@EnableMethodSecurity // enables the customization spring security support at the method level.
public class SecurityConfiguration {
	/*
	 * Configure a bean to customize spring security filter change.
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// 1. disable CSRF protection
		http.csrf(csrf -> csrf.disable());

		// 2. Session creation policy - stateless
		http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// 3. Enable Basic Authentication
		http.httpBasic(Customizer.withDefaults());

		// 4. Add Authentication
		http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

		return http.build();
	}
}
