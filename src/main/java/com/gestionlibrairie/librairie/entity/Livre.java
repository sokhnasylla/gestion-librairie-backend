package com.gestionlibrairie.librairie.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;
    private String theme;
    private String genre;
    private String etat;
    @Lob
    private String details;
    private Date datePublication;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private String image;


    @OneToMany(mappedBy = "livre")
    List<Emprunt> livreEmpruntes;
}
