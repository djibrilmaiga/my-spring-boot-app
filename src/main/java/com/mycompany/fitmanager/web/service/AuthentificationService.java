package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.dto.AuthenticationRequest;
import com.mycompany.fitmanager.web.dto.AuthenticationResponse;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.UtilisateurRepository;
import com.mycompany.fitmanager.web.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder; // Encoder pour les mots de passe, gère le chiffrement et la comparaison sécurisée.
    private final JwtService jwtService; // Service pour générer et valider les tokens JWT.
    private final AuthenticationManager authenticationManager; // Gère le processus d'authentification dans Spring Security.

    /**
    * Méthode qui gère l'authentification des utilisateurs
    */
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        try{
            // Utilise le AuthenticationManager pour authentifier l'utilisateur à partir du login et du mot de passe.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getLogin(), // Login (ou email) fourni par l'utilisateur.
                            authenticationRequest.getPassword() // Mot de passe fourni par l'utilisateur.
                    )
            );
            // Si l'authentification réussit, elle est stockée dans le contexte de sécurité de Spring.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Génère un token JWT pour l'utilisateur authentifié.
            String token = jwtService.generateJwtToken(authentication);

            // Retourne une réponse d'authentification contenant le token JWT.
            return new AuthenticationResponse(token);
        } catch (BadCredentialsException e){
            // Si l'authentification échoue, une exception personnalisée est lancée.
            // Cela permet de renvoyer un message d'erreur plus précis à l'utilisateur.
            throw new ResourceNotFoundException("Email ou mot de passe incorrect");
        }
    }

}