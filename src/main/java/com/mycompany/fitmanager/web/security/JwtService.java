package com.mycompany.fitmanager.web.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mycompany.fitmanager.web.exception.TokenExpiredException;
import com.mycompany.fitmanager.web.entity.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mycompany.fitmanager.web.security.JwtUtil.JWT_EXPIRATION;
import static com.mycompany.fitmanager.web.security.JwtUtil.JWT_SECRET;

@Service
public class JwtService {

    /**
     * Méthode pour extraire le nom d'utilisateur du token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, DecodedJWT::getSubject);
    }

    /**
     * Méthode générique pour extraire une réclamation du token.
     */
    public <T> T extractClaim(String token, Function<DecodedJWT, T> claimsResolver) {
        // Décoder le token
        final DecodedJWT decodedJWT = JWT.decode(token);

        // Extraire la réclamation spécifique
        return claimsResolver.apply(decodedJWT);
    }

    /**
     * Génère un token JWT pour un utilisateur authentifié.
     */
    public String generateJwtToken(Authentication authentication) {
        // Récupérer l'utilisateur authentifié
        Utilisateur user = (Utilisateur) authentication.getPrincipal();
        String username = user.getUsername();
        // Récupérer les rôles de l'utilisateur
        String roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

        return JWT.create()
                .withSubject(username)
                .withClaim("roles", roles)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .sign(algorithm);
    }

    /**
     * Vérifie si le token est valide.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Vérifie si le token est expiré.
     */
    private boolean isTokenExpired(String token) {
        try {
            final DecodedJWT decodedJWT = JWT.decode(token);
            final Date expirationDate = decodedJWT.getExpiresAt();
            if (expirationDate != null && expirationDate.before(new Date())) {
                throw new TokenExpiredException("Le token d'authentification a expiré.");
            }
            return false;
        } catch (TokenExpiredException e) {
            return true;
        }
    }

    /**
     * Vérifie si le token a trois parties séparées par des points.
     */
    public boolean isTokenWellFormed(String token) {
        String[] parts = token.split("\\.");
        return parts.length == 3;
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}