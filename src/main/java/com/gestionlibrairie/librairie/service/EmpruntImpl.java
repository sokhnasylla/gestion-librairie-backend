package com.gestionlibrairie.librairie.service;

import com.gestionlibrairie.librairie.dto.EmpruntRes;
import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.Emprunt;
import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.entity.User;
import com.gestionlibrairie.librairie.repository.EmpruntRepo;
import com.gestionlibrairie.librairie.repository.LivreRepo;
import com.gestionlibrairie.librairie.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmpruntImpl implements EmpruntService {

    @Autowired
    private EmpruntRepo empruntRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LivreRepo livreRepo;

    @Override
    public List<ReqRes> getAllEmprunts() {
        List<Emprunt> emprunts = empruntRepo.findAll();
        List<ReqRes> reqResList = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            ReqRes reqRes = new ReqRes();
            reqRes.setUser(emprunt.getUser());
            // Add other properties as needed
            reqResList.add(reqRes);
        }
        return reqResList;
    }

    @Override
    public Emprunt createEmprunt(EmpruntRes empruntRes) {
        User user = userRepo.findById(empruntRes.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Livre livre = livreRepo.findById(empruntRes.getLivreId())
                .orElseThrow(() -> new RuntimeException("Livre not found"));

        Emprunt emprunt = new Emprunt();
        emprunt.setUser(user);
        emprunt.setLivre(livre);
        return empruntRepo.save(emprunt);
    }
}
