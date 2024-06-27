package com.gestionlibrairie.librairie.controller;

import com.gestionlibrairie.librairie.dto.ReqRes;
import com.gestionlibrairie.librairie.entity.User;
import com.gestionlibrairie.librairie.repository.UserRepo;
import com.gestionlibrairie.librairie.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("admin/users")
    public List<ReqRes>getAllUsers(){
        return userService.getAllUsers();
    }
    @PutMapping("admin/updateuser/{id}")
    User updateUser(@RequestBody User user , @PathVariable Long id){
        return userService.updateUser(user,id);
    }

    @DeleteMapping("admin/deleteuser/{id}")
    public String deleteUser(@PathVariable Long id){
            userService.deleteUserById(id);
        return "User supprimé";
    }
    @GetMapping("public/searchByLogin")
    public ResponseEntity<List<ReqRes>> searchByLogin(@RequestParam String login) {
        List<ReqRes> result = userService.searchedByLogin(login);
        return ResponseEntity. ok(result);
    }
  @GetMapping("admin/detailsuser/{id}")
        public ResponseEntity<User> detailsUser(@PathVariable Long id){
          User userDetails = userService.detailsUser(id);
          return ResponseEntity.ok(userDetails);
     }
  }




