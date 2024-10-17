package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Utilisateur;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // POST
    public Utilisateur createUser(Utilisateur newUser){
        String pwd = newUser.getPassword();
        newUser.setPassword(passwordEncoder().encode(pwd));
        return utilisateurRepository.save(newUser);
    }

    // GET ALL
    public List<Utilisateur> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUserById(Integer userId){
        return utilisateurRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable à l'id : " + userId));
    }

    // UPDATE
    public Utilisateur updateUser (Integer userId, Utilisateur newUser){
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable à l'id : " + userId));

        utilisateur.setLogin(newUser.getLogin());
        utilisateur.setPassword(passwordEncoder().encode(newUser.getPassword()));
        utilisateur.setRole(newUser.getRole());

        return utilisateurRepository.save(utilisateur);
    }

    // DELETE
    public void deleteUserById (Integer userId){
        utilisateurRepository.deleteById(userId);
    }
    

}
