package com.gestionlibrairie.librairie.dto;

public class EmpruntRes {
    private Long id;
    private Long userId;
    private Long livreId;
    private Integer quantiteLivre;
    private String etat;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLivreId() {
        return livreId;
    }

    public void setLivreId(Long livreId) {
        this.livreId = livreId;
    }

    public Integer getQuantiteLivre() {
        return quantiteLivre;
    }

    public void setQuantiteLivre(Integer quantiteLivre) {
        this.quantiteLivre = quantiteLivre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
