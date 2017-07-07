package com.ericsson.etk.test.auth;

import com.ericsson.etk.test.exception.UnsupportedAuthenticationMethod;
import com.ericsson.etk.test.exception.UserDoesNotExistException;
import org.junit.Test;

/**
 * Created by eadrdam on 06.07.17..
 */
public class AuthenticationServiceTest {
    @Test
    public void authenicate() throws Exception {

        AuthenticationService authenticationService = new AuthenticationService();
        try {
            authenticationService.authenticate("BASIC Sm9objpwYXNzd29yZA==");

        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        } catch (UnsupportedAuthenticationMethod unsupportedAuthenticationMethod) {
            unsupportedAuthenticationMethod.printStackTrace();
        }

    }

}