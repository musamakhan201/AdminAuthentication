package com.seo.app.AdminAuthentication.services;

import com.seo.app.AdminAuthentication.domains.AdminRegistrationDomain;
import com.seo.app.AdminAuthentication.repository.AdminRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewProfileService {
    @Autowired
    AdminRegistrationRepository adminRegistrationRepository;

    public AdminRegistrationDomain viewUser(int id){
        AdminRegistrationDomain admin=adminRegistrationRepository.getOne(id);
        return admin;
    }

}
