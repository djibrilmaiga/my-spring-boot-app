package com.mycompany.fitmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Instructeur extends Agent {
    // Propriétés
    @NotNull
    @Column(nullable = false)
    private String specialite;

    // Contient la liste des séances animées par un instructeur
    @OneToMany(mappedBy = "instructeur", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "Instructeur-seances")
    private List<Seance> seances = new ArrayList<>();

}