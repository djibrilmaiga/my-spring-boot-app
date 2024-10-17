package com.mycompany.fitmanager.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity // Active la configuration de sécurité pour les applications Web avec Spring Security.
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter; // Filtre d'authentification pour les tokens JWT.
    private final AuthenticationProvider authenticationProvider; // Fournisseur d'authentification pour valider les utilisateurs.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Désactive la protection CSRF car l'application utilise des tokens JWT qui ne nécessitent pas de protection CSRF.
                .cors(cors -> cors.configurationSource(getCorsConfigurationSource())) // Configure la gestion CORS.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Permet l'accès non authentifié aux routes d'authentification (par exemple : login, inscription).
                        .requestMatchers("/api/admin").hasRole("ADMIN") // Restreint l'accès à cette route aux utilisateurs avec le rôle ADMIN uniquement.
                        .requestMatchers("/api/manager").hasRole("MANAGER") // Restreint l'accès à cette route aux utilisateurs avec le rôle MANAGER uniquement.
                        .anyRequest().authenticated() // Toutes les autres requêtes doivent être authentifiées.
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configure l'application pour ne pas utiliser de session HTTP. Elle est "stateless", donc chaque requête doit être authentifiée indépendamment.
                )
                .authenticationProvider(authenticationProvider) // Définit le fournisseur d'authentification qui sera utilisé pour la validation des utilisateurs.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Ajoute le filtre JWT avant le filtre standard d'authentification UsernamePassword.

        return httpSecurity.build(); // Construit et renvoie la chaîne de filtres de sécurité.
    }

    @Bean
    public CorsConfigurationSource getCorsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Autoriser uniquement l'origine http://localhost:3000
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));

        // Définit les méthodes HTTP autorisées.
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST", "PUT","DELETE", "OPTIONS"));

        // Applique les valeurs par défaut pour les origines, les en-têtes, et les durées de vie des CORS.
        corsConfiguration.applyPermitDefaultValues();

        // Enregistre la configuration CORS pour toutes les routes.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source; // Renvoie la source de configuration CORS.
    }
}