package com.gestionlibrairie.librairie.controller;

import com.gestionlibrairie.librairie.dto.EmpruntRes;
import com.gestionlibrairie.librairie.entity.Emprunt;
import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.entity.User;
import com.gestionlibrairie.librairie.enums.ETAT_EMP;
import com.gestionlibrairie.librairie.repository.EmpruntRepo;
import com.gestionlibrairie.librairie.repository.LivreRepo;
import com.gestionlibrairie.librairie.repository.UserRepo;
import com.gestionlibrairie.librairie.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class EmpruntController {

    @Autowired
    private EmpruntRepo empruntRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LivreRepo livreRepo;

    @Autowired
    private EmpruntService empruntService;

    @PostMapping("/emprunts")
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
        emprunt.setQuantiteLivre(quantiteLivre);
        emprunt.setEtat(ETAT_EMP.EMPRUNTE.toString());
        empruntRepo.save(emprunt);

        return ResponseEntity.ok("Emprunt enregistré avec succès");
    }


    @GetMapping("admin/emprunts")
    public ResponseEntity<List<EmpruntRes>> getAllEmprunts() {
        List<EmpruntRes> empruntResList = empruntService.getAllEmprunts();
        return ResponseEntity.ok(empruntResList);
    }
    @DeleteMapping("admin/deleteEmprunt/{id}")
    public String deleteEmprunt(@PathVariable Long id){
        empruntService.deleteEmpruntById(id);
        return "emprunt supprimé";
    }

}
