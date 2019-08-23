package com.library.api.web.controller;


import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.svc.contracts.AppUserSvc;
import com.library.api.security.AccountService;
import com.library.api.security.jwt.DecodeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api( description="API pour des opérations CRUD sur les livres de la bibliothèque.")
@RestController
@RequestMapping("/appUsers")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AppUserSvc appUserSvc;


    /**
     *
     * @param userForm
     * @return
     */
    @ApiOperation(value = "Enregistre un nouvel utilisateur")
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm){
        return accountService.saveUser(
                userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
    }

    /**
     *
     * @return
     */
    @ApiOperation(value = "Récupère tous les utilisateurs enregistrés")
    @GetMapping()
    List<AppUser> findAllUsers() {
        return appUserSvc.findAllUsers();
    }

    /**
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "Récupère un utilisateur par son username")
    @GetMapping("/selected/")
    AppUser findUserByUsername(HttpServletRequest request) {
        DecodeToken decodeToken = new DecodeToken();
        String username = decodeToken.decodeUsername(request);
        return appUserSvc.findByUsername(username);
    }

}

@Data
class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
}
