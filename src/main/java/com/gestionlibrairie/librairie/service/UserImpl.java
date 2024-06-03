package com.gestionlibrairie.librairie.service;

import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.entity.User;
import com.gestionlibrairie.librairie.enums.ETAT_USER;
import com.gestionlibrairie.librairie.exception.MyNotFoundExceptionClass;
import com.gestionlibrairie.librairie.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    private ReqRes reqRes;
    @Override
    public List<ReqRes> getAllUsers() {
        List<User> users = userRepo.findAllByEtatNot(ETAT_USER.SUPPRIME.toString());
        List<ReqRes> reqResList = new ArrayList<>();
        for(User user : users){
            ReqRes reqRes = new ReqRes();
            reqRes.setPrenom(user.getPrenom());
            reqRes.setNom(user.getNom());
            reqRes.setRole(user.getRole());
            reqRes.setLogin(user.getLogin());
            reqResList.add(reqRes);
        }
        return reqResList;
    }
    @Override
    public User updateUser(User user, Long id) {
        Optional<User> searchUpdateUser =userRepo.findById(id);

        if(searchUpdateUser.isEmpty()){
            return null;
        }
        User userFound = searchUpdateUser.get();
        userFound.setPrenom(user.getPrenom());
        userFound.setNom(user.getNom());
        userFound.setLogin(user.getLogin());
        userFound.setPassword(user.getPassword());
        userFound.setRole(user.getRole());
        return userRepo.save(userFound);
    }

    @Override
    public User detailsUser(Long id) {
        Optional<User>userSearchedDetails=userRepo.findById(id);
        if (userSearchedDetails.isEmpty()){
            throw  new MyNotFoundExceptionClass("User non trouvé avec l'identifiant");
        }
        return userSearchedDetails.get();

    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User>userSearched=userRepo.findById(id);
        if(userSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("User non trouvé avec l'identifiant");
        }
        User userFound = userSearched.get();
        userFound.setEtat(ETAT_USER.SUPPRIME.toString());
        userRepo.save(userFound);
    }

    @Override
    public List<ReqRes> searchedByLogin(String login) {
        Optional<User> userOptional = userRepo.findByLogin(login);
        List<ReqRes> result = new ArrayList<>();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            ReqRes reqRes = new ReqRes();
            reqRes.setNom(user.getNom());
            reqRes.setPrenom(user.getPrenom());
            reqRes.setRole(user.getRole());
            reqRes.setLogin(user.getLogin());

            // Définir d'autres propriétés de ReqRes en fonction de Livre si nécessaire
            result.add(reqRes);
        }
        return result;
    }
}
