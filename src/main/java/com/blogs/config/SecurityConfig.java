package com.blogs.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blogs.model.RoleEnum;
import com.blogs.serviceimpl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(SecurityConfig.class);

	@Autowired
	MyUserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void configGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		// Các User trong bộ nhớ (MEMORY).
		logger.info(RoleEnum.USER.toString());
		auth.inMemoryAuthentication().withUser("user1").password("12345").roles(RoleEnum.USER.toString());
		auth.inMemoryAuthentication().withUser("admin").password("12345").roles(RoleEnum.ADMIN.toString());

		// Các User trong Database
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		// Các trang không yêu cầu login
		http.authorizeRequests().antMatchers("/", 
				"/register", "/register//regitrationConfirm", "/login",
				"/userInfo","/viewpost/**"
		).permitAll();

		http.authorizeRequests().anyRequest().authenticated();

		// Trang /userInfo yêu cầu phải login với vai trò USER hoặc ADMIN.
		http.authorizeRequests().antMatchers("/api/follow")
				.access("hasAnyRole('ROLE_" + RoleEnum.USER + "', 'ROLE_" + RoleEnum.ADMIN + "')");

		http.authorizeRequests().antMatchers("/admin*").access("hasRole('ROLE_" + RoleEnum.ADMIN + "')");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/access_denied");

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()
				// Submit URL của trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login").defaultSuccessUrl("/userInfo").failureUrl("/login?error=true")
				.usernameParameter("username").passwordParameter("password");
		
		// Cấu hình cho Logout Page.
		http.authorizeRequests().and().logout().logoutUrl("/logout");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
}
