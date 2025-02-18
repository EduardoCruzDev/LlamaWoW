package org.llamawow.infra;

import org.llamawow.repository.auth.AccountRepository;
import org.llamawow.service.impl.CustomUserDetailsService;
import org.llamawow.service.impl.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authz->
                        authz.requestMatchers("/login","/register","/","/downloads").permitAll()
                                .requestMatchers(HttpMethod.GET,"/ranking").permitAll()
                                .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
                                .requestMatchers("/swagger-ui/**","/swagger-ui/index.html", "/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management ->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountRepository accountRepository, EncryptionService encryptionService) {
        // Configura tu servicio de detalles de usuario
        return new CustomUserDetailsService(accountRepository, encryptionService);  // Servicio de autenticación personalizado
    }
}

