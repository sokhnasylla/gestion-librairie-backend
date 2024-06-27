package com.gestionlibrairie.librairie.controller;

import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.Livre;
import com.gestionlibrairie.librairie.enums.ETAT_LIVRE;
import com.gestionlibrairie.librairie.enums.ETAT_USER;
import com.gestionlibrairie.librairie.repository.LivreRepo;
import com.gestionlibrairie.librairie.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController

public class LivreController {
    @Autowired
    private LivreRepo livreRepo;
    @Autowired
    private LivreService livreService;

    @GetMapping("/public/livre")
    public List<ReqRes> getAllLivres(){
        return livreService.getAllLivres();
    }
        @PostMapping("/admin/addlivre")
        public ResponseEntity<Object> signUp(@RequestBody ReqRes livreRequest){
            Livre livreSave = new Livre();
            livreSave.setTitre(livreRequest.getTitre());
            livreSave.setAuteur(livreRequest.getAuteur());
            livreSave.setTheme(livreRequest.getTheme());
            livreSave.setGenre(livreRequest.getGenre());
            livreSave.setAuteur(livreRequest.getAuteur());
            livreSave.setDatePublication(livreRequest.getDatePublication());
            if (livreRequest.getImage() != null && livreRequest.getImage().startsWith("data:image")) {
                livreSave.setImage(livreRequest.getImage().split(",")[1]);
            } else {
                livreSave.setImage(livreRequest.getImage());
            }

            livreSave.setDetails(livreRequest.getDetails());
            livreSave.setEtat(ETAT_LIVRE.DISPO.toString());


            return ResponseEntity.ok(livreRepo.save(livreSave));
        }

    @PutMapping("/admin/updatelivre/{id}")
    Livre updateLivre(@RequestBody Livre livre,@PathVariable Long id){
        return livreService.updateLivre(livre, id);
    }
    @DeleteMapping("/admin/deletelivre/{id}")
    public String deleteLivre(@PathVariable Long id){
        livreService.deleteLivreById(id);
        return "Livre supprimé";
    }

    @GetMapping("public/searchlivre")
    public ResponseEntity<List<ReqRes>> searchByTitre(@RequestParam String titre) {
        List<ReqRes> result = livreService.searchedByTitre(titre);
        return ResponseEntity.ok(result);
    }
    @GetMapping("admin/livreDetails/{id}")
    public ResponseEntity<Livre> detailsLivre(@PathVariable Long id){
        Livre livreDetails = livreService.detailsLivre(id);

        return ResponseEntity.ok(livreDetails);
    }





//    @GetMapping("/user/alone")
//    public ResponseEntity<Object> userAlone(){
//        return ResponseEntity.ok("Users Alone can access this Api only");
//    }
//    @GetMapping("/adminuser/both")
//    public ResponseEntity<Object> bothAdminUsersApi(){
//        return ResponseEntity.ok("Both Admin and Users can access this Api ");
//    }

}
