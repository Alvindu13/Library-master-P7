package com.library.api.security;

import com.library.api.persistance.dao.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private AccountService accountService;

    /**
     * Cette méthode de spring security permet de retourner un objet user grâce à un UserName.
     * On créé un UserDetails propre à spring security à partir de notree User (AppUser) à nous.
     * @param username
     * @return On retourne l'USER Spring Security avec des paramètres par défauts (username, password, authorities) attendus par spring
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = accountService.loadUserByUsername(username);

        if(appUser==null) throw new UsernameNotFoundException("Invalid user" + username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}
