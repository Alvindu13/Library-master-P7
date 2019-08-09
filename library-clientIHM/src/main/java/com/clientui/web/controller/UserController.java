package com.clientui.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {


    /**
     * Page d'accueil du web site
     * @return
     */
    @RequestMapping(value = { "/", "home"}, method = RequestMethod.GET)
    public String index(@RequestParam(value = "username", defaultValue = "") String username) {

        return "Accueil";
    }

}
