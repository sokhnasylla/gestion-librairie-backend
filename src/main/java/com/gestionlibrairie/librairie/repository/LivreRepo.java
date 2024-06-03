package com.gestionlibrairie.librairie.repository;

import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepo extends JpaRepository<Livre,Long> {
    List<Livre> findByTitre(String titre);
    List<Livre> findAllByEtatNot(String etat);
}
