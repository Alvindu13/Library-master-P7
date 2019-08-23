package com.library.persistance.svc.svcImpl;

import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.repository.AppUserRepository;
import com.library.persistance.svc.contracts.AppUserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserSvcImpl implements AppUserSvc {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
