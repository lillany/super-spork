package com.ericsson.etk.test.auth;

import com.ericsson.etk.test.domain.User;
import com.ericsson.etk.test.exception.UnsupportedAuthenticationMethod;
import com.ericsson.etk.test.exception.UserDoesNotExistException;
import com.ericsson.etk.test.log.LogService;
import com.ericsson.etk.test.storage.UserStorage;
import com.sun.jersey.core.util.Base64;

/**
 * Created by eadrdam on 06.07.17..
 */
public class AuthenticationService {

    private final UserStorage userStorage;
    private LogService logService = new LogService();


    public AuthenticationService() {
        userStorage = new UserStorage();
    }

    public void authenicate(String username, String password) throws UserDoesNotExistException {

        User userByUsername = userStorage.getUserByUsername(username);
        if (null == userByUsername)
            throw new UserDoesNotExistException();

        if(!userByUsername.getPassword().equals(password))
            throw new UserDoesNotExistException();
    }

    public void authenticate(String auth) throws UserDoesNotExistException, UnsupportedAuthenticationMethod {
        String username;
        String password;

        if (auth != null && auth.startsWith("BASIC")) {
            String[] authenticationArray = auth.split(" ");
            if (authenticationArray.length < 2) {
                throw new UnsupportedAuthenticationMethod();
            }
            String decoded = null;

            decoded = new String(Base64.base64Decode(authenticationArray[1]));

            logService.debug("SERVLET", "Authentication attempt" + auth + " " + decoded);
            username = decoded.substring(0, decoded.indexOf(":"));
            password = decoded.substring(decoded.indexOf(":")+1);
            logService.info("SERVLET", "Authentication attempt for username " + username);
            this.authenicate(username, password);

        } else {
            throw new UnsupportedAuthenticationMethod();
        }
    }
}
