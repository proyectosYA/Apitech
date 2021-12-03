package com.empresa.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.empresa.api.dominio.security.Filter;
 

@SpringBootApplication
public class ApiTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTechApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.headers().frameOptions().disable();
			http.csrf().disable()
				.addFilterAfter(new Filter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()  
				.antMatchers(HttpMethod.POST, "/login").permitAll() 
				.antMatchers(HttpMethod.POST,"/usuario/add-usuario").permitAll()
			     .antMatchers("/").permitAll() 
	            .and().authorizeRequests().antMatchers("/h2/**").permitAll();
             //.anyRequest().authenticated();
     
		}
	}
}

