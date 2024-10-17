package com.mycompany.fitmanager.web.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor // Génère un constructeur avec les arguments pour les dépendances marquées comme "final".
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService; // Service pour gérer la création et validation des tokens JWT.
    private final UserDetailsService userDetailsService; // Service permettant de charger les informations utilisateur à partir de l'email.

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // Requête HTTP reçue.
            @NonNull HttpServletResponse response, // Réponse HTTP à envoyer.
            @NonNull FilterChain filterChain // Chaine de filtres, pour passer à la suite si tout est OK.
    ) throws ServletException, IOException {
        // Récupère l'en-tête d'autorisation de la requête.
        final String authHeader = request.getHeader(JwtUtil.AUTH_HEADER);
        final String jwt; // Contiendra le token JWT extrait de l'en-tête.
        final String userEmail; // Email de l'utilisateur extrait du JWT.

        // Vérifie que l'en-tête d'autorisation est présent et commence par le préfixe attendu.
        if (authHeader == null || !authHeader.startsWith(JwtUtil.PREFIX)) {
            // Si ce n'est pas le cas, on passe au filtre suivant sans toucher à la requête.
            filterChain.doFilter(request, response);
            return; // Sort de la méthode pour éviter tout traitement ultérieur.
        } else {
            // Extrait le JWT à partir de l'en-tête (en enlevant le préfixe "Bearer ").
            jwt = authHeader.substring(7);
        }

        // Extrait l'email de l'utilisateur (ou "username") à partir du JWT.
        userEmail = jwtService.extractUsername(jwt);

        // Si l'email n'est pas nul et qu'aucune authentification n'est présente dans le contexte de sécurité.
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Charge les détails de l'utilisateur à partir de l'email.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Vérifie si le JWT est valide pour cet utilisateur.
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Crée un token d'authentification avec les détails de l'utilisateur.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                // Associe les détails supplémentaires liés à la requête (comme l'IP ou l'agent utilisateur).
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Met à jour le contexte de sécurité avec ce token d'authentification.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continue le traitement en appelant le filtre suivant dans la chaîne.
        filterChain.doFilter(request, response);
    }
}