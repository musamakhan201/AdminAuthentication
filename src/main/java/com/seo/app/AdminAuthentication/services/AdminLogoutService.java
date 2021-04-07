package com.seo.app.AdminAuthentication.services;

import com.seo.app.AdminAuthentication.domains.AdminRegistrationDomain;
import com.seo.app.AdminAuthentication.repository.AdminRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLogoutService {
    private final Logger log = LoggerFactory.getLogger(AdminLogInService.class);

    @Autowired
    AdminRegistrationRepository adminRegistrationRepository;

    public String logoutUser(int id)
    {
        AdminRegistrationDomain admin = adminRegistrationRepository.getOne(id);
        admin.setLoggedIn(false);
        adminRegistrationRepository.save(admin);
        String responseMessage = "Admin Logged Out With ID: "+admin.getUser_id();
        log.info(responseMessage);
        return responseMessage;
    }
}
