package com.gestionlibrairie.librairie.service;

import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.entity.User;
import com.gestionlibrairie.librairie.enums.ETAT_LIVRE;
import com.gestionlibrairie.librairie.enums.ETAT_USER;
import com.gestionlibrairie.librairie.exception.MyNotFoundExceptionClass;
import com.gestionlibrairie.librairie.repository.LivreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class LivreImpl implements LivreService{
    @Autowired
    private LivreRepo livreRepo;

    @Override
    public List<ReqRes> getAllLivres() {
        List<Livre> livres = livreRepo.findAllByEtatNot(ETAT_LIVRE.SUPPRIME.toString());
        List<ReqRes> reqResList = new ArrayList<>();
        for(Livre livre : livres){
            ReqRes reqRes = new ReqRes();
            reqRes.setImage(livre.getImage());
            reqRes.setTitre(livre.getTitre());
            reqRes.setAuteur(livre.getAuteur());
            reqRes.setTheme(livre.getTheme());
            reqRes.setGenre(livre.getGenre());
            reqRes.setDatePublication(livre.getDatePublication());
            reqResList.add(reqRes);
        }
        return reqResList;
    }

    @Override
    public Livre detailsLivre(Long id) {
        Optional<Livre>livreSearchedDetails = livreRepo.findById(id);
        if (livreSearchedDetails.isEmpty()){
            throw new MyNotFoundExceptionClass("Livre non trové avec l'id");
        }
        return livreSearchedDetails.get();
    }

    @Override
    public Livre updateLivre(Livre livre, Long id) {
        Optional<Livre> searchUpdateLivre = livreRepo.findById(id);
        if(searchUpdateLivre.isEmpty()){
            return null;
        }
        Livre livreFound =searchUpdateLivre.get();
        livreFound.setTitre(livre.getTitre());
        livreFound.setAuteur(livre.getAuteur());
        livreFound.setGenre(livre.getGenre());
        livreFound.setTheme(livre.getTheme());
        livreFound.setDatePublication(livre.getDatePublication());
        livreFound.setImage(livre.getImage());
        return livreRepo.save(livreFound);

    }

    @Override
    public void deleteLivreById(Long id) {
        Optional<Livre>livreSearched=livreRepo.findById(id);
        if(livreSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("Livre non trouvé avec l'identifiant");
        }
        Livre livreFound = livreSearched.get();
        livreFound.setEtat(ETAT_LIVRE.SUPPRIME.toString());

    }

    @Override
    public List<ReqRes> searchedByTitre(String titre) {
        List<Livre> livres = livreRepo.findByTitre(titre);
        List<ReqRes> result = new ArrayList<>();
        for (Livre livre : livres) {
            ReqRes reqRes = new ReqRes();
            reqRes.setTitre(livre.getTitre());
            reqRes.setAuteur(livre.getAuteur());
            reqRes.setTheme(livre.getTheme());
            reqRes.setGenre(livre.getGenre());
            reqRes.setDatePublication(livre.getDatePublication());
            reqRes.setImage(livre.getImage());

            // Définir d'autres propriétés de ReqRes en fonction de Livre si nécessaire
            result.add(reqRes);
        }
        return result;
}
}

