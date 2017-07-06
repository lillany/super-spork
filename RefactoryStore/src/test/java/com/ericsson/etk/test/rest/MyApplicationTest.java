package com.ericsson.etk.test.rest;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by eadrdam on 30.06.17..
 */
public class MyApplicationTest {
    @Test
    public void testGetClasses() throws Exception {
        MyApplication myApplication = new MyApplication();
        Set<Class<?>> classes = myApplication.getClasses();
        Assert.assertTrue(classes.contains(RefactoryStoreServlet.class));
    }

}