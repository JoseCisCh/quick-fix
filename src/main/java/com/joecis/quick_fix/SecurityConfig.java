package com.joecis.quick_fix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import com.joecis.quick_fix.user.AppUserDetailsService;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(configurer -> configurer.disable())
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/register").permitAll()
                    .requestMatchers("/**").fullyAuthenticated()
                    .anyRequest()
                    .authenticated())
            .httpBasic(Customizer.withDefaults())
            .cors(Customizer.withDefaults())
            .securityContext(securityContext -> securityContext
                    .securityContextRepository(new HttpSessionSecurityContextRepository()))
            .sessionManagement(session -> {
                session.maximumSessions(1).maxSessionsPreventsLogin(true);
                session.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession);
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
            })
            .logout((logout) -> {
                logout.logoutUrl("/logout");
                logout.invalidateHttpSession(true);
                logout.deleteCookies("JSESSIONID");
                logout.permitAll();
                logout.logoutSuccessHandler((request, response, authentication)-> {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Logged out successfully");
                });
            });

    return http.build();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AppUserDetailsService appUserDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

}
