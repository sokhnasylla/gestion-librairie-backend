package com.gestionlibrairie.librairie.repository;

import com.gestionlibrairie.librairie.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepo extends JpaRepository<Emprunt,Long > {
    List<Emprunt> findAllByEtatNot(String etat);
}
