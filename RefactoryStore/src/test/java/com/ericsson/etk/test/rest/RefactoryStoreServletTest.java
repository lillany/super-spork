package com.ericsson.etk.test.rest;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by eadrdam on 30.06.17..
 */
public class RefactoryStoreServletTest extends TestCase {
    @Test
    public void testGetMsg() throws Exception {
        System.out.println("testing");
        RefactoryStoreServlet refactoryStoreServlet = new RefactoryStoreServlet();
        String testMsg = "This is test message";
        System.out.println(refactoryStoreServlet.UnknownQueryResponse(testMsg));
       Assert.assertEquals(testMsg, refactoryStoreServlet.UnknownQueryResponse(testMsg).getEntity().toString());

    }



}