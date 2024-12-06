package com.mycompany.fitmanager.web;

import com.mycompany.fitmanager.web.entity.Utilisateur;
import com.mycompany.fitmanager.web.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FitmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitmanagerApplication.class, args);
    }

/*    @Bean
    public CommandLineRunner initDefaultUser(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(utilisateurRepository.findByLogin("ADMIN").isEmpty()){
                Utilisateur adminUser = Utilisateur.builder()
                        .login("gymsadmin")
                        .password(passwordEncoder.encode("gymsadmin2024"))
                        .role(Utilisateur.RoleUser.ADMIN)
                        .build();
                utilisateurRepository.save(adminUser);
                System.out.println("Utilisateur par défaut crée !");
            }
        };
    }*/
}