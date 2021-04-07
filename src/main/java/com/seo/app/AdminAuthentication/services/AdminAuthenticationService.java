package com.seo.app.AdminAuthentication.services;

import com.seo.app.AdminAuthentication.Dto.AuthenticationDto;
import com.seo.app.AdminAuthentication.domains.AdminRegistrationDomain;
import com.seo.app.AdminAuthentication.repository.AdminRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationService {
    private final Logger log = LoggerFactory.getLogger(AdminLogInService.class);

    @Autowired
    AdminRegistrationRepository adminRegistrationRepository;

    public String authenticateUser(AuthenticationDto authenticationDto)
    {
        AdminRegistrationDomain admin=adminRegistrationRepository.findByEmail(authenticationDto.getEmail());
        if (admin!=null)
        {
            if (admin.getPassword().equals(authenticationDto.getPassword()))
            {
                if (admin.isEnabled())
                {
                    admin.setLoggedIn(true);
                    adminRegistrationRepository.save(admin);
                    String responseMessage = "Admin Logged In With ID: "+admin.getUser_id();
                    log.info(responseMessage);
                    return responseMessage;
                }
                else{
                    String responseMessage = "Please first confirm your registration to login";
                    log.info(responseMessage);
                    return responseMessage;
                }
            }
        }
        else
        {
            String responseMessage = "Username is incorrect";
            log.info(responseMessage);
            return responseMessage;
        }
        return "Password is Incorrect";
    }
}
