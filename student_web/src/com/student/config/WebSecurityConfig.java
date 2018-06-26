package com.student.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("jorge")
			.password("$2a$10$ia6RXsu03QE1YOo.2ymJwekTwruXFE4k6TtsVC29.RnIG9HD0FRMy")
			.roles("USER", "ADMIN")
			.and().passwordEncoder(new BCryptPasswordEncoder());
		
	}

	public static void main(String[] args){
		System.out.println(new BCryptPasswordEncoder().encode("jorge"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("web security configuration");
		
		http.authorizeRequests()
		//.antMatchers("/login*").anonymous()
		.antMatchers("/login*","/assets/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/")
		.failureUrl("/login?error=true")
		.and()
		.logout().logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID", "SESSION");
		
	}
}
