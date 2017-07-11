package com.ericsson.etk.test.rest;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import javax.ws.rs.core.Response;

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
       refactoryStoreServlet.getGalleryView(null,"Atkinson Bonner",null,null,null,"BASIC Sm9objpwYXNzd29yZA==");


    }

    @Test
    public void testGetInfoMsg() throws Exception {
        System.out.println("testing");
        RefactoryStoreServlet refactoryStoreServlet = new RefactoryStoreServlet();
        String testMsg = "This is test message";
        System.out.println(refactoryStoreServlet.UnknownQueryResponse(testMsg));
        Assert.assertEquals(testMsg, refactoryStoreServlet.UnknownQueryResponse(testMsg).getEntity().toString());
        Response xx = refactoryStoreServlet.getInfoView("0c96943a-9164-4937-a74a-a7724f47740e", "BASIC Sm9objpwYXNzd29yZA==");
        System.out.println(xx.getEntity().toString());


    }



}