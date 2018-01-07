package br.com.taldi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.taldi.usuario.UsuarioDetailService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDetailService usuarioDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuario/protected/**").hasAnyRole("ADMIN","CLIENTE")
		.and().formLogin()
		.loginPage("/usuario/login")
		.loginProcessingUrl("/app-login")
		.usernameParameter("app_usuario")
		.passwordParameter("app_senha")
		.defaultSuccessUrl("/usuario/protected/uconsumidora")
		.and().logout().logoutUrl("/app-logout")
		.logoutSuccessUrl("/usuario/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
