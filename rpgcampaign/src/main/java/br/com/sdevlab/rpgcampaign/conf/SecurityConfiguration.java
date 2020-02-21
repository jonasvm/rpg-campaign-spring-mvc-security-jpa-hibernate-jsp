package br.com.sdevlab.rpgcampaign.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.sdevlab.rpgcampaign.daos.UserDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDAO userDao;

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
			.antMatchers("/users/details").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/campaigns").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/campaigns").hasRole("ADMIN")
			.antMatchers("/campaigns/**").hasRole("ADMIN")
			.antMatchers("/users/form").permitAll().
			antMatchers(HttpMethod.POST, "/users").permitAll()
			.antMatchers("/contact").permitAll()
			.antMatchers("/logout").permitAll()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDao).passwordEncoder(new BCryptPasswordEncoder());
	}

}
