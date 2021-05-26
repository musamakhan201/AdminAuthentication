package com.seo.app.AdminAuthentication.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.seo.app.AdminAuthentication.Dto.AdminRegistrationDto;
import com.seo.app.AdminAuthentication.domains.AdminRegistrationDomain;
import com.seo.app.AdminAuthentication.domains.ConfirmationTokenDomain;
import com.seo.app.AdminAuthentication.repository.AdminRegistrationRepository;
import com.seo.app.AdminAuthentication.repository.ConfirmationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class AdminRegistrationService {
    private final Logger log = LoggerFactory.getLogger(AdminRegistrationService.class);

    @Autowired
    private AdminRegistrationRepository adminRegistrationRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    @Autowired
    private EmailService emailSenderService;

    public String registerUser(AdminRegistrationDto adminRegistrationDto)
    {
            AdminRegistrationDomain adminRegistrationDomain;
            AdminRegistrationDomain admin = adminRegistrationRepository.findByEmail(adminRegistrationDto.getEmail());
            if (admin!=null)
            {
                if (admin.getEmail().equals(adminRegistrationDto.getEmail()))
                {
                    String responseMessage = "Admin Already Exists With This Email";
                    log.info(responseMessage);
                    return responseMessage;
                }
                else{
                    adminRegistrationDomain=objectMapper.convertValue(adminRegistrationDto,AdminRegistrationDomain.class);
                    adminRegistrationRepository.save(adminRegistrationDomain);
                    SimpleMailMessage mailMessage=new SimpleMailMessage();
                    ConfirmationTokenDomain confirmationToken = new ConfirmationTokenDomain(adminRegistrationDomain);
                    confirmationTokenRepository.save(confirmationToken);
                    mailMessage.setTo("musamakhan201@gmail.com");
                    mailMessage.setSubject("Complete Registration For SEO Optimization App!");
                    mailMessage.setFrom("seo.optimization.helper@gmail.com");
                    mailMessage.setText("To confirm your account, please click here : "
                            +"http://139.59.80.110:8187/seo/confirm-account?token="+confirmationToken.getConfirmationToken());
                    emailSenderService.sendEmail(mailMessage);
                    String responseMessage = "Email has been sent to you";
                    log.info(responseMessage);
                    return responseMessage;
                }
            }
            else{
//                AdminRegistrationDomain admin1 = adminRegistrationRepository.findByUsername(adminRegistrationDto.getEmail());
                adminRegistrationDomain=objectMapper.convertValue(adminRegistrationDto,AdminRegistrationDomain.class);
                adminRegistrationRepository.save(adminRegistrationDomain);
                SimpleMailMessage mailMessage=new SimpleMailMessage();
                ConfirmationTokenDomain confirmationToken = new ConfirmationTokenDomain(adminRegistrationDomain);
                confirmationTokenRepository.save(confirmationToken);
                mailMessage.setTo("musamakhan201@gmail.com");
                mailMessage.setSubject("Complete Registration For SEO Optimization App!");
                mailMessage.setFrom("seo.optimization.helper@gmail.com");
                mailMessage.setText("To confirm your account, please click here : "
                        +"http://139.59.80.110:8187/seo/confirm-account?token="+confirmationToken.getConfirmationToken());
                emailSenderService.sendEmail(mailMessage);
                String responseMessage = "Email has been sent to you";
                log.info(responseMessage);
                return responseMessage;
            }

    }


}
