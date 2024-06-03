package com.gestionlibrairie.librairie.service.auth;

import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.User;
import com.gestionlibrairie.librairie.enums.ETAT_USER;
import com.gestionlibrairie.librairie.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            User user = new User();
            user.setNom(registrationRequest.getNom());
            user.setPrenom(registrationRequest.getPrenom());
            user.setLogin(registrationRequest.getLogin());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(registrationRequest.getRole());
            user.setEtat(ETAT_USER.ACTIF.toString());
            User userResult = userRepo.save(user);
            if (userResult != null && userResult.getId()>0) {
                resp.setUser(userResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
    public ReqRes SignIn(ReqRes signInRequest){
        ReqRes response = new ReqRes();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getLogin(),signInRequest.getPassword()));
            var user = userRepo.findByLogin(signInRequest.getLogin()).orElseThrow();
            System.out.println("User is "+user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(),user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("successfully sign in");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        String ourLogin = jwtUtils.ExtractUsername(refreshTokenReqiest.getToken());
        User users = userRepo.findByLogin(ourLogin).orElseThrow();
        if(jwtUtils.isTokenValid(refreshTokenReqiest.getToken(),users)){
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("successfully refresed token");
        }
        response.setStatusCode(500);
        return response;
    }
}

