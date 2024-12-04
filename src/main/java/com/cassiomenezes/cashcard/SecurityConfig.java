package com.cassiomenezes.cashcard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		final HttpSecurity httpSecurity = http.authorizeHttpRequests(request -> request
				.requestMatchers("/cashcards/**")
				.hasRole("CARD-OWNER"));
		http.httpBasic(Customizer.withDefaults());
		http.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
		User.UserBuilder users = User.builder();
		UserDetails sarah = users
				.username("sarah1")
				.password(passwordEncoder.encode("abc123"))
				.roles("CARD-OWNER") // No roles for now
				.build();
		UserDetails hankOwnsNoCards = users
				.username("hank-owns-no-cards")
				.password(passwordEncoder.encode("qrs456"))
				.roles("NON-OWNER")
				.build();

		return new InMemoryUserDetailsManager(sarah, hankOwnsNoCards);
	}
}
