package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Utilisateur;
import com.mycompany.fitmanager.web.exception.ResourceNotFoundException;
import com.mycompany.fitmanager.web.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    //@Autowired
   //private BCryptPasswordEncoder passwordEncoder;

    // GET ALL
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    // GET ID
    public Utilisateur findById(Integer id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé à l'ID : " + id));
    }

    // POST
    public Utilisateur create(Utilisateur utilisateur) {
       // utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword())); // Hashing du mot de passe
        return utilisateurRepository.save(utilisateur);
    }

    // PUT
    public Utilisateur update(Integer id, Utilisateur updatedUtilisateur) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setLogin(updatedUtilisateur.getLogin());
            utilisateur.setPassword(updatedUtilisateur.getPassword());
            //utilisateur.setPassword(passwordEncoder.encode(updatedUtilisateur.getPassword())); // Update password
            utilisateur.setRole(updatedUtilisateur.getRole());
            return utilisateurRepository.save(utilisateur);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    // DELETE
    public void deleteById(Integer id) {
        utilisateurRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Utilisateur introuvable à l'ID : " + id));
        utilisateurRepository.deleteById(id);
    }

    public Boolean existsByLogin (String login){
        return utilisateurRepository.existsByLogin(login);
    }
}
