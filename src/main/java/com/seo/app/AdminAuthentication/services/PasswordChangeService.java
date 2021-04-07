package com.seo.app.AdminAuthentication.services;

import com.seo.app.AdminAuthentication.Dto.PasswordUpdateDto;
import com.seo.app.AdminAuthentication.domains.AdminRegistrationDomain;
import com.seo.app.AdminAuthentication.repository.AdminRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordChangeService {
    private final Logger log = LoggerFactory.getLogger(AdminRegistrationService.class);

    @Autowired
    AdminRegistrationRepository adminRegistrationRepository;


    public String changePassword(PasswordUpdateDto passwordUpdateDto)
    {
        AdminRegistrationDomain admin = adminRegistrationRepository.getOne(passwordUpdateDto.getUser_id());
        if (admin.getPassword().equals(passwordUpdateDto.getOld_password()))
        {
            admin.setPassword(passwordUpdateDto.getNew_password());
            adminRegistrationRepository.save(admin);
            String responseMessage = "Password CHanged of ID: "+admin.getUser_id();
            log.info(responseMessage);
            return responseMessage;
        }
        String responseMessage = "Old Password is Incorrect";
        log.info(responseMessage);
        return responseMessage;
    }
}
