package com.gestionlibrairie.librairie.controller;

import com.gestionlibrairie.librairie.entity.Emprunt;
import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.entity.User;
import com.gestionlibrairie.librairie.repository.EmpruntRepo;
import com.gestionlibrairie.librairie.repository.LivreRepo;
import com.gestionlibrairie.librairie.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntRepo empruntRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LivreRepo livreRepo;

    @PostMapping
    public ResponseEntity<?> createEmprunt(@RequestParam Long livreId,
                                           @RequestParam String userLogin,
                                           @RequestParam Integer quantiteLivre) {
        Optional<Livre> livreOptional = livreRepo.findById(livreId);
        if (!livreOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Livre not found");
        }

        Optional<User> userOptional = userRepo.findByLogin(userLogin);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livreOptional.get());
        emprunt.setUser(userOptional.get());
        emprunt.setQuantiteLivre(quantiteLivre); // Utilisation du paramètre quantiteLivre
        empruntRepo.save(emprunt);

        return ResponseEntity.ok("Emprunt enregistré avec succès");
    }
}
