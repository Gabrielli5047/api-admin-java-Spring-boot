package br.com.desafio2.ilab.group5.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private EntryPoint entryPoint;

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(entryPoint).and()
		.authorizeRequests()				
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers("/actuator/**").permitAll()
		.anyRequest().authenticated().and().cors();
	
		http.addFilterBefore(new Filters(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();

	}
}
