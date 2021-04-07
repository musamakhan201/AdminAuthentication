package com.seo.app.AdminAuthentication.services;

import com.seo.app.AdminAuthentication.Dto.UpdateTipsDto;
import com.seo.app.AdminAuthentication.domains.TipsDomain;
import com.seo.app.AdminAuthentication.repository.TipsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTipsService {
    private final Logger log = LoggerFactory.getLogger(AdminRegistrationService.class);

    @Autowired
    TipsRepository tipsRepository;


    public String updateTip(UpdateTipsDto updateTipsDto)
    {
        TipsDomain tip = tipsRepository.getOne(updateTipsDto.getTips_id());
        tip.setTitle(updateTipsDto.getTitle());
        tip.setDescription(updateTipsDto.getDescription());
        tipsRepository.save(tip);
        String responseMessage = "Tip Updated With ID: "+tip.getTips_id();
        log.info(responseMessage);
        return responseMessage;
    }
}
