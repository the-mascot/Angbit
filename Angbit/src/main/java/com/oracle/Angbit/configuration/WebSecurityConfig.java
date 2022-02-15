package com.oracle.Angbit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oracle.Angbit.security.CustomAuthenticationFilter;
import com.oracle.Angbit.security.CustomLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/").permitAll()
//				.anyRequest().authenticated()
//				.and()
//			.formLogin()
//				.loginPage("/loginForm")
//				.successForwardUrl("/")
//				//.failureUrl("lginForm")
//				.permitAll()
//				.and()
//				.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//			.logout()
//				.permitAll();
//			
//	}
//	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
//		CustomAuthenticationFilter cusotmAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
//		cusotmAuthenticationFilter.setFilterProcessesUrl("LoginProcess");
//		cusotmAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
//		cusotmAuthenticationFilter.afterPropertiesSet();
//		return cusotmAuthenticationFilter;
//	}
//	
//	@Bean
//	public CustomLoginSuccessHandler customLoginSuccessHandler() {
//		return new CustomLoginSuccessHandler();
//	}
//	
}