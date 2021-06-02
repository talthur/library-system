package br.com.talthur.librarysystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.talthur.librarysystem.security.repository.UserRepository;
import br.com.talthur.librarysystem.services.SSUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private SSUserDetailsService userDetailService;
	
	@Autowired
	private UserRepository UserRepository;
	
	
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new SSUserDetailsService(UserRepository);
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().		
		antMatchers("/").access("hasAnyAuthority('USER','ADMIN')").
		antMatchers("/home").access("hasAnyAuthority('USER','ADMIN')").
		antMatchers("/admin").access("hasAuthority('ADMIN')").
		anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll().and().httpBasic();
		
		
		//check this
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("Arthur").password(passwordEncoder().encode("1234")).authorities("ADMIN").and()
//		.withUser("user").password(passwordEncoder().encode("1234")).authorities("USER");
		
		auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
		
	}
	
	
	
	

}
