package com.spring.course.springit.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;

	public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.requestMatchers(EndpointRequest.to("info")).permitAll()
				.requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
				.antMatchers("/actuator/").hasRole("ADMIN")
				.antMatchers("/").permitAll()
				.antMatchers("/link/submit").hasRole("USER")
//				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.usernameParameter("email") // because spring is waiting for a "username" field (id in html form)
				.and()
			.logout()
			.and()
			.rememberMe(); // saves a cookie that will keep you logged in / can be also storaged
	}

	//EndpointRequest (to match the endpoints)

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
