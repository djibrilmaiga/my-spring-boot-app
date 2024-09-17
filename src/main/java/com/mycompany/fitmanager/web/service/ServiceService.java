package com.mycompany.fitmanager.web.service;

import com.mycompany.fitmanager.web.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    // Creer un service
    public com.mycompany.fitmanager.web.entity.Service creerService(com.mycompany.fitmanager.web.entity.Service service) {
        return serviceRepository.save(service);
    }
    // Obtenir un service
    public com.mycompany.fitmanager.web.entity.Service obtenirServiceParId(Integer id){
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service non trouvé à l'id : " + id));
    }
    // Obtenir tous les services
    public List<com.mycompany.fitmanager.web.entity.Service> obtenirTousLesServices() {
        return serviceRepository.findAll();
    }
    // Mettre à jour un service
    public com.mycompany.fitmanager.web.entity.Service modifierService(com.mycompany.fitmanager.web.entity.Service service){
        return serviceRepository.save(service);
    }
    // Supprimer un service
    public void supprimerService(Integer id){
        serviceRepository.deleteById(id);
    }
}
