package com.gestionlibrairie.librairie.service;

import com.gestionlibrairie.librairie.dto.EmpruntRes;
import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.Emprunt;

import java.util.List;

public interface EmpruntService {
    List<ReqRes> getAllEmprunts();
    Emprunt createEmprunt(EmpruntRes empruntRes);
}
