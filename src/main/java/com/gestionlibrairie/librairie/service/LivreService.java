package com.gestionlibrairie.librairie.service;

import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.Livre;

import java.util.List;

public interface LivreService {
    List<ReqRes> getAllLivres();
    Livre detailsLivre(Long id);
    Livre updateLivre(Livre livre, Long id);
    void deleteLivreById(Long id);
    List<ReqRes> searchedByTitre(String titre);
}
