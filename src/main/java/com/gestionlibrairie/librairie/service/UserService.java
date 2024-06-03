package com.gestionlibrairie.librairie.service;

import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.User;

import java.util.List;

public interface UserService {
    List<ReqRes> getAllUsers();
    User updateUser(User user, Long id);
    User detailsUser(Long id);
    void deleteUserById(Long id);
    List<ReqRes> searchedByLogin(String login);
}
