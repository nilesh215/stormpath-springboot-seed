package com.stormpath.controller;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.servlet.account.AccountResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Nilesh Bhosale on 6/28/15.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    String home(HttpServletRequest request) {

        String name = "World";

        Account account = AccountResolver.INSTANCE.getAccount(request);
        if (account != null) {
            name = account.getGivenName();
        }

        return "Hello " + name + "!";
    }

}
