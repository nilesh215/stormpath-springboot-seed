package com.stormpath.service;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountStatus;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.directory.CustomData;
import com.stormpath.sdk.resource.ResourceException;
import com.strompath.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nilesh Bhosale on 6/28/15.
 */
@Component("loginService")
public class LoginService {
    @Autowired
    Client client;

    @Autowired
    Application application;

    /**
     * To register a user on storm path
     * @param user
     * @return
     */
    public Account register(User user){
    //Create the account object
        Account account = client.instantiate(Account.class);

    //Set the account properties
        account.setGivenName(user.getFirstName());
        account.setSurname(user.getLastName());
        account.setUsername(user.getUsername()); //optional, defaults to email if unset
        account.setEmail(user.getEmailId());
        account.setPassword(user.getPassword());
        account.setStatus(AccountStatus.UNVERIFIED);

        CustomData customData = account.getCustomData();
        customData.put("customData", user.getCustomData());

    //Create the account using the existing Application object
        account= application.createAccount(account);
        return account;
    }

    /**
     * To authenticate a user by email and password
     * @param user
     * @return
     */
    public Account login(User user){
    //Create an authentication request using the credentials
        AuthenticationRequest request = new UsernamePasswordRequest(user.getEmailId(), user.getPassword());
        Account account=null;
    //Now let's authenticate the account with the application:
        try {
            AuthenticationResult result = application.authenticateAccount(request);
             account = result.getAccount();
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus() + " " + ex.getMessage());
        }
        return account;
    }
}
