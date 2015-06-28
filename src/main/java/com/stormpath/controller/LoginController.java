package com.stormpath.controller;

import com.stormpath.service.LoginService;
import com.strompath.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nilesh Bhosale on 6/28/15.
 */
@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody  String register(@RequestBody User user) {
    //Register User
     return loginService.register(user).getEmailVerificationToken().getValue();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody  Object login(@RequestBody User user) {
        //Register User
        return loginService.login(user).getFullName();
    }
}
