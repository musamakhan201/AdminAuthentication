package com.seo.app.AdminAuthentication.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.seo.app.AdminAuthentication.domains.AdminRegistrationDomain;
import com.seo.app.AdminAuthentication.Dto.AdminRegistrationDto;
import com.seo.app.AdminAuthentication.repository.AdminRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRegistrationService {
    private final Logger log = LoggerFactory.getLogger(AdminRegistrationService.class);

    @Autowired
    AdminRegistrationRepository adminRegistrationRepository;


    @Autowired
    ObjectMapper objectMapper;


    public String registerUser(AdminRegistrationDto adminRegistrationDto)
    {
        AdminRegistrationDomain adminRegistrationDomain;
        List<AdminRegistrationDomain> admins = adminRegistrationRepository.findAll();
        for (AdminRegistrationDomain admin : admins)
        {
            if (admin.getEmail().equals(adminRegistrationDto.getEmail()))
            {
                String responseMessage = "Admin Already Exists With This Email";
                log.info(responseMessage);
                return responseMessage;
            }
            else if (admin.getUsername().equals(adminRegistrationDto.getUsername()))
            {
                String responseMessage = "Admin Already Exists With This Username";
                log.info(responseMessage);
                return responseMessage;
            }
        }
        adminRegistrationDomain=objectMapper.convertValue(adminRegistrationDto,AdminRegistrationDomain.class);
        adminRegistrationRepository.save(adminRegistrationDomain);
        String responseMessage = "Admin has been register";
        log.info(responseMessage);
        return responseMessage;
    }

}
