package br.com.springboot;


//aula 6 tema 5
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
	 

	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 @Bean
	 public AuthenticationManager authenticationManager(
	         AuthenticationConfiguration authConfig) throws Exception {
	     return authConfig.getAuthenticationManager();
	 }
    
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
				
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("12345"))
				.roles("USUARIO")
				.build();
			UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("12345"))
				.roles("USUARIO", "ADMININISTRADOR")
				.build();
			return new InMemoryUserDetailsManager(user, admin);

}
	
@SuppressWarnings("deprecation")
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		http
			.authorizeRequests()
				.requestMatchers(HttpMethod.GET, "/nota-entrada").hasRole("ADMINISTRADOR")
				.requestMatchers(HttpMethod.GET, "/nota-saida").hasRole("ADMINISTRADOR")
				.requestMatchers(HttpMethod.GET, "/estoque").hasRole("ADMINISTRADOR")
			.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login");
		return http.build();
	}


}