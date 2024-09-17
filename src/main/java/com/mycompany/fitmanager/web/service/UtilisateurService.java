package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.entity.Utilisateur;
import com.mycompany.fitmanager.web.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur createUser(Utilisateur utilisateur){
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUser(Integer id){
        Utilisateur user = utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return utilisateurRepository.save(user);
    }

    public void  deleteUser (Integer id){
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur getUser(Integer id){
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé à l'id : " + id));
    }

    public List<Utilisateur> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUserByLogin(String login){
        return utilisateurRepository.findByLogin(login);
    }
}
