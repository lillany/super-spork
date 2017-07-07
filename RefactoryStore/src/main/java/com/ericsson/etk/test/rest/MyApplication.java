package com.ericsson.etk.test.rest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eadrdam on 30.06.17..
 */
public class MyApplication extends Application {

    /**
     * Override for method in order to add servlet classes
     *
     * @return set with all servlet classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(RefactoryStoreServlet.class);
        return classes;
    }
}


