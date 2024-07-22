package com.library.app.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends DelegatingWebMvcConfiguration {

    @Autowired
    private TokenFilter tokenFilter;
    @Value("${spring.ip.frontend.url}")
    private String frontendIpUrl;
    @Value("${spring.local.frontend.url}")
    private String frontendUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(h ->
                h.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/librarian/get").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/librarian/get/{id}").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/librarian/delete/{id}").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedHeaders("Authorization", "Content-Type")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedOrigins(frontendIpUrl, frontendUrl);
    }
}



