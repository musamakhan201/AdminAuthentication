package com.seo.app.AdminAuthentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seo.app.AdminAuthentication.Dto.AdminUpdateDto;
import com.seo.app.AdminAuthentication.domains.AdminRegistrationDomain;
import com.seo.app.AdminAuthentication.repository.AdminRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUpdateService {
    private final Logger log = LoggerFactory.getLogger(AdminRegistrationService.class);

    @Autowired
    AdminRegistrationRepository adminRegistrationRepository;


    public String updateUser(AdminUpdateDto adminUpdateDto)
    {
        AdminRegistrationDomain admin = adminRegistrationRepository.getOne(adminUpdateDto.getUser_id());
        admin.setFirst_name(adminUpdateDto.getFirst_name());
        admin.setLast_name(adminUpdateDto.getLast_name());
        admin.setAddress(adminUpdateDto.getAddress());
        admin.setEmail(adminUpdateDto.getEmail());
        adminRegistrationRepository.save(admin);
        String responseMessage = "Admin Updated With ID: "+admin.getUser_id();
        log.info(responseMessage);
        return responseMessage;
    }
}

