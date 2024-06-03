package com.gestionlibrairie.librairie.repository;

import com.gestionlibrairie.librairie.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepo extends JpaRepository<Emprunt,Long> {
}
