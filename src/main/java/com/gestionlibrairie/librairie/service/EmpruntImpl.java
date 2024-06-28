package com.gestionlibrairie.librairie.service;

import com.gestionlibrairie.librairie.dto.EmpruntRes;
import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.Emprunt;
import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.entity.User;
import com.gestionlibrairie.librairie.enums.ETAT_EMP;
import com.gestionlibrairie.librairie.exception.MyNotFoundExceptionClass;
import com.gestionlibrairie.librairie.repository.EmpruntRepo;
import com.gestionlibrairie.librairie.repository.LivreRepo;
import com.gestionlibrairie.librairie.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpruntImpl implements EmpruntService {

    @Autowired
    private EmpruntRepo empruntRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LivreRepo livreRepo;

    @Override
    public List<EmpruntRes> getAllEmprunts() {
        List<Emprunt> emprunts = empruntRepo.findAllByEtatNot(ETAT_EMP.RENDU.toString());
        List<EmpruntRes> empruntResList = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            EmpruntRes empruntRes = new EmpruntRes();
            empruntRes.setId(emprunt.getId());
            empruntRes.setUserId(emprunt.getUser().getId());
            empruntRes.setLivreId(emprunt.getLivre().getId());
            empruntRes.setQuantiteLivre(emprunt.getQuantiteLivre());
            empruntResList.add(empruntRes);
        }
        return empruntResList;
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

    @Override
    public void deleteEmpruntById(Long id) {
        Optional<Emprunt>empruntSearched = empruntRepo.findById(id);
        if(empruntSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("emprunt non trouvé avec l'identifiant");
        }
        Emprunt empruntFound = empruntSearched.get();
        empruntFound.setEtat(ETAT_EMP.RENDU.toString());
        empruntRepo.save(empruntFound);
    }
}
