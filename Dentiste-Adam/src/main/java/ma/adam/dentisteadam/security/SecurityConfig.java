package ma.adam.dentisteadam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
//    {
//        return new InMemoryUserDetailsManager(
//        User.withUsername("user10").password(passwordEncoder.encode("1234")).roles("USER").build()
//        );
//
//
//    }
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
//        httpSecurity.authorizeHttpRequests().requestMatchers("/new_patient").hasAnyRole("USER");
        httpSecurity.formLogin().loginPage("/login").permitAll().successHandler(myAuthenticationSuccessHandler());
        httpSecurity.authorizeHttpRequests().requestMatchers("/").permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers("/home").permitAll();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/index").permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers("/dashboard").hasRole("DOCTEUR");
        httpSecurity.authorizeHttpRequests().requestMatchers("/*").hasRole("DOCTEUR");

        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
        return httpSecurity.build();

    }
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            String redirectUrl = "/index";  // Default redirect

            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_DOCTEUR")) {
                    redirectUrl = "/dashboard";
                    break;
                } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    redirectUrl = "/admin-dashboard";
                    break;
                }
            }

            response.sendRedirect(redirectUrl);
        };
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
