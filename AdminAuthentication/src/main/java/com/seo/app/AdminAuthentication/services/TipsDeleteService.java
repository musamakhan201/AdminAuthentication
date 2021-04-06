package com.seo.app.AdminAuthentication.services;

import com.seo.app.AdminAuthentication.domains.TipsDomain;
import com.seo.app.AdminAuthentication.repository.TipsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipsDeleteService {
    private final Logger log = LoggerFactory.getLogger(AdminRegistrationService.class);

    @Autowired
    TipsRepository tipsRepository;


    public String deleteTip(int id)
    {
        TipsDomain tip = tipsRepository.getOne(id);
        tipsRepository.delete(tip);
        String responseMessage = "Tip Deleted With ID: "+tip.getTips_id();
        log.info(responseMessage);
        return responseMessage;
    }
}
