package com.seo.app.AdminAuthentication.controller;

import com.seo.app.AdminAuthentication.Dto.AdminLogInDto;
import com.seo.app.AdminAuthentication.Dto.AdminRegistrationDto;
import com.seo.app.AdminAuthentication.Dto.AuthenticationDto;
import com.seo.app.AdminAuthentication.Dto.TipsDto;
import com.seo.app.AdminAuthentication.domains.TipsDomain;
import com.seo.app.AdminAuthentication.repository.TipsRepository;
import com.seo.app.AdminAuthentication.services.AdminLogInService;
import com.seo.app.AdminAuthentication.services.AdminRegistrationService;
import com.seo.app.AdminAuthentication.services.ConnectionService;
import com.seo.app.AdminAuthentication.services.TipsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("seo")
public class AdminController {
    private final Logger log = LoggerFactory.getLogger(com.seo.app.AdminAuthentication.controller.AdminController.class);

    @Autowired
    private AdminRegistrationService adminRegistrationService;

    @Autowired
    ConnectionService connectionService;

    @Autowired
    private AdminLogInService adminLogInService;

    @Autowired
    private TipsService tipsService;

    @Autowired
    TipsRepository tipsRepository;

    @RequestMapping(value = "/get/tips",method = RequestMethod.GET)
    public Iterable<TipsDomain> getTips(){
        return tipsRepository.findAll();
    }

    @RequestMapping(value = "/add/tips",method = RequestMethod.POST)
    public String addTips(@RequestBody TipsDto tipsDto){
        log.info("POST Call received at tips/Tip added with DTO" + tipsDto);
        return tipsService.addTips(tipsDto);
    }

    @RequestMapping(value = "/register/admin", method = RequestMethod.POST)
    public String registerUser(@RequestBody AdminRegistrationDto adminRegistrationDto) {
        log.info("POST Call received at admin/register with DTO" + adminRegistrationDto);
        return adminRegistrationService.registerUser(adminRegistrationDto);
    }

    @RequestMapping(value = "/login/admin", method = RequestMethod.POST)
    public String addLoginUser(@RequestBody AdminLogInDto adminLogInDto) {
        log.info("POST Call received at admin/add with DTO" + adminLogInDto);
        return adminLogInService.loginUser(adminLogInDto);
    }

    @PostMapping(path = "/auth")
    public String getStatus(@RequestBody final AuthenticationDto authenticationDto) {
        List<String> users_list = new ArrayList<>();
        List<String> password_list = new ArrayList<>();
        int position = -1;
        String status = null;
        try {
            Connection connection = connectionService.createConnection();
            PreparedStatement statement = connection.prepareStatement("select username,password from admins");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users_list.add(rs.getString("username"));
                password_list.add(rs.getString("password"));
            }
        } catch (Exception e) {
            log.error(e.toString());

        }
        String e = authenticationDto.getUsername();
        String p = authenticationDto.getPassword();
        for (int i = 0; i < users_list.size(); i++) {


            if (e.equals(users_list.get(i)))
                position = i;
        }
        if (position == -1) {
            status = "username doesn't exist";
        } else if (position >= 0) {
            if (p.equals(password_list.get(position)))
                status = "admin user exist and password is true";
            else status = "admin user exist and password is false";

        }
        return status;
    }

}
