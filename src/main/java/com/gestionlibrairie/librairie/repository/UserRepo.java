package com.gestionlibrairie.librairie.repository;

import com.gestionlibrairie.librairie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User>findByLogin(String login);
    List<User> findAllByEtatNot(String etat);
}
