package com.clientui.web.controller;

import com.clientui.beans.UserBean;
import com.clientui.web.proxies.MicroserviceConsumerProxy;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    MicroserviceConsumerProxy mConsumerProxy;

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }


    @GetMapping("/register")
    public String displayRegisterForm(Model model) {

        UserBean userBean = new UserBean();

        model.addAttribute("userBean", userBean);
        return "/registerPage";
    }

    @PostMapping("/register")
    public String registerUser( @ModelAttribute("userBean") UserBean userBean) {

        /*userBean.setId(5L);
        userBean.setActive(1);
        userBean.setPermissions("USER");
        userBean.setRoles("USER");*/
        mConsumerProxy.newUser(userBean);
        return "/about";
    }



    /*@GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("users", mConsumerProxy.findAllUsers());
        return "/login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("userBean") UserBean userBean) {
        System.out.println("test");
        return "/login";
    }*/




    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
