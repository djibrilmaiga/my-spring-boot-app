package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Technicien extends Agent {
    // Propriétés
    @OneToMany(mappedBy = "technicien", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "technicien-maintenances")
    private List<Maintenance> maintenances = new ArrayList<>();

}