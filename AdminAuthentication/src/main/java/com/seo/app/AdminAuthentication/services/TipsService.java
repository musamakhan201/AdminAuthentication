package com.seo.app.AdminAuthentication.services;

import com.seo.app.AdminAuthentication.Dto.TipsDto;
import com.seo.app.AdminAuthentication.domains.TipsDomain;
import com.seo.app.AdminAuthentication.repository.TipsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipsService {
    private final Logger log = LoggerFactory.getLogger(TipsService.class);

    @Autowired
    TipsRepository tipsRepository;

    public String addTips(TipsDto tipsDto)
    {
        TipsDomain tipsDomain=new TipsDomain();
        tipsDomain.setTitle(tipsDto.getTitle());
        tipsDomain.setDescription(tipsDto.getDescription());
        tipsRepository.save(tipsDomain);
        String responseMessage = "Tips has been register with ID " + tipsDomain.getTips_id();
        log.info(responseMessage);
        return responseMessage;
    }

}
