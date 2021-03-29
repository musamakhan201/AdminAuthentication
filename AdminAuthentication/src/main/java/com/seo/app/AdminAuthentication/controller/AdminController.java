package com.seo.app.AdminAuthentication.controller;

import com.seo.app.AdminAuthentication.Dto.*;
import com.seo.app.AdminAuthentication.domains.TipsDomain;
import com.seo.app.AdminAuthentication.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seo")
public class AdminController {
    private final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminRegistrationService adminRegistrationService;

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private AdminLogInService adminLogInService;

    @Autowired
    private TipsService tipsService;

    @Autowired
    private AdminLogoutService adminLogoutService;

    @Autowired
    private AdminAuthenticationService adminAuthenticationService;

    @Autowired
    private AdminUpdateService adminUpdateService;

    @Autowired
    private UpdateTipsService updateTipsService;

    @Autowired
    private PasswordChangeService passwordChangeService;

    @Autowired
    private TipsDeleteService tipsDeleteService;

    @RequestMapping(value = "/delete/tip", method = RequestMethod.POST)
    public String deleteTips(int id){
        log.info("POST Call received at Tips/delete with ID" + id);
        return tipsDeleteService.deleteTip(id);
    }

    @RequestMapping(value = "/password/change", method = RequestMethod.PUT)
    public String changePassword(@RequestBody PasswordUpdateDto passwordUpdateDto){
        log.info("POST Call received at Admin/change Password with DTO" + passwordUpdateDto);
        return passwordChangeService.changePassword(passwordUpdateDto);
    }

    @RequestMapping(value = "/tips/update", method = RequestMethod.PUT)
    public String updateTip(@RequestBody UpdateTipsDto updateTipsDto) {
        log.info("POST Call received at Tips/update with DTO" + updateTipsDto);
        return updateTipsService.updateTip(updateTipsDto);
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.PUT)
    public String updateUser(@RequestBody AdminUpdateDto adminUpdateDto) {
        log.info("POST Call received at admin/update with DTO" + adminUpdateDto);
        return adminUpdateService.updateUser(adminUpdateDto);
    }

    @RequestMapping(value = "/tips",method = RequestMethod.GET)
    public Iterable<TipsDomain> getTips(){
        return tipsService.getTips();
    }

    @RequestMapping(value = "/tips",method = RequestMethod.POST)
    public String addTips(@RequestBody TipsDto tipsDto){
        log.info("POST Call received at tips/Tip added with DTO" + tipsDto);
        return tipsService.addTips(tipsDto);
    }

    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public String registerUser(@RequestBody AdminRegistrationDto adminRegistrationDto) {
        log.info("POST Call received at admin/register with DTO" + adminRegistrationDto);
        return adminRegistrationService.registerUser(adminRegistrationDto);
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String addLoginUser(@RequestBody AdminLogInDto adminLogInDto) {
        log.info("POST Call received at admin/add with DTO" + adminLogInDto);
        return adminLogInService.loginUser(adminLogInDto);
    }

    @PostMapping(path = "/auth")
    public String getStatus(@RequestBody AuthenticationDto authenticationDto) {
        log.info("POST Call received at admin/login with DTO" + authenticationDto);
        return adminAuthenticationService.authenticateUser(authenticationDto);
    }

    @PostMapping(path = "/logout")
    public String logOut(int user_id) {
        log.info("POST Call received at admin/logout with ID" + user_id);
        return adminLogoutService.logoutUser(user_id);
    }

}
