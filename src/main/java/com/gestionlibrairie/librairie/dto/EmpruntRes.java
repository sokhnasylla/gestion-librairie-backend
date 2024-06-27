package com.gestionlibrairie.librairie.dto;

public class EmpruntRes {
    private Long id;
    private Long userId;
    private Long livreId;

    // Getters and setters
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
}
