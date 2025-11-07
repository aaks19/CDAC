package com.healthcare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	private final PasswordEncoder encoder;

//	@Bean
//	UserDetailsService userDetailsService() {
//		User patient=new User("rama@gmail.com", encoder.encode("12345"), List.of(new SimpleGrantedAuthority("ROLE_PATIENT")));
//		User doctor=new User("kiran@gmail.com", encoder.encode("1345"), List.of(new SimpleGrantedAuthority("ROLE_DOCTOR")));
//		InMemoryUserDetailsManager mgr = new InMemoryUserDetailsManager(patient,doctor);
//		return mgr;
//	}

	@Bean
	SecurityFilterChain configureFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request.requestMatchers("/v3/api-docs/**", "/swagger-ui/**","/users/**")
						.permitAll().requestMatchers("/doctors/**").hasRole("DOCTOR").requestMatchers("/patients/**")
						.hasRole("PATIENT").requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated());

		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(configure -> configure.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
}
