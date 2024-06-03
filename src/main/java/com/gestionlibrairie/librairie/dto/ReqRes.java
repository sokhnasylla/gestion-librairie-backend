package com.gestionlibrairie.librairie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    private int statusCode;
    private String message;
    private String error;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String role;
    private String etat;
    private String titre;
    private String auteur;
    private String theme;
    private String genre;
    private Date datePublication;
    private String details;
    private String image;

   private List<Livre>livres;
   private User user;
}
