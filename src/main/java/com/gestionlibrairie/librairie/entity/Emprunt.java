package com.gestionlibrairie.librairie.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "emprunt")
@Getter
@Setter
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantiteLivre;
    private String etat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", quantiteLivre=" + quantiteLivre +
                ", user=" + (user != null ? user.getId() : null) +
                ", livre=" + (livre != null ? livre.getId() : null) +
                ", etat=" + etat +
                '}';
    }
}
