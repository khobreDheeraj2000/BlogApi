package com.blogapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.blogapi.security.CustomUserDetailService;
import com.blogapi.security.JwtAuthenticationEntryPoint;
import com.blogapi.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

	@Autowired
	CustomUserDetailService customUserDetailService;
	

	
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf()
		.disable()
		.authorizeHttpRequests().requestMatchers("/login").permitAll().requestMatchers("/register").permitAll()
		.requestMatchers(HttpMethod.GET).permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(daoAuthenticationProvider());
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
		return defaultSecurityFilterChain ;
	}
  
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
//	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider ;
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
//	 @Bean
//	    public FilterRegistrationBean coresFilter() {
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//	        CorsConfiguration corsConfiguration = new CorsConfiguration();
//	        corsConfiguration.setAllowCredentials(true);
//	        corsConfiguration.addAllowedOriginPattern("*");
//	        corsConfiguration.addAllowedHeader("Authorization");
//	        corsConfiguration.addAllowedHeader("Content-Type");
//	        corsConfiguration.addAllowedHeader("Accept");
//	        corsConfiguration.addAllowedMethod("POST");
//	        corsConfiguration.addAllowedMethod("GET");
//	        corsConfiguration.addAllowedMethod("DELETE");
//	        corsConfiguration.addAllowedMethod("PUT");
//	        corsConfiguration.addAllowedMethod("OPTIONS");
//	        corsConfiguration.setMaxAge(3600L);
//
//	        source.registerCorsConfiguration("/**", corsConfiguration);
//
//	        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//
//	        bean.setOrder(-110);
//
//	        return bean;
//	    }
}
