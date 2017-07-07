package com.ericsson.etk.test.rest;

import com.ericsson.etk.test.auth.AuthenticationService;
import com.ericsson.etk.test.domain.Book;
import com.ericsson.etk.test.exception.UnsupportedAuthenticationMethod;
import com.ericsson.etk.test.exception.UserDoesNotExistException;
import com.ericsson.etk.test.storage.DataStorage;
import com.ericsson.etk.test.store.Basket;
import com.ericsson.etk.test.log.LogService;
import com.ericsson.etk.test.view.GalleryView;
import com.ericsson.etk.test.view.ObjectView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/store")
public class RefactoryStoreServlet {

    LogService logService = new LogService();
    AuthenticationService authenticationService = new AuthenticationService();


    @GET
    @Path("/{param}")
    public Response UnknownQueryResponse(@PathParam("param") String parameter) {

        logService.debug("SERVLET", "Unknown request with path: " + parameter);
        return Response.ok().entity(parameter).build();
    }

    @GET
    @Path("/gallery")
    public Response getGalleryView(
            @MatrixParam("year") String year,
            @MatrixParam("author") String author,
            @MatrixParam("minScore") String minScore,
            @MatrixParam("price") String price,
            @MatrixParam("orderby") String orderby,
            @HeaderParam("authorization") String authentication

    ) {

        System.out.println(authentication);
        try {
            authenticationService.authenticate(authentication);
        } catch (UserDoesNotExistException e) {
            return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\":\"USER_NOT_FOUND\"}").build();
        } catch (UnsupportedAuthenticationMethod unsupportedAuthenticationMethod) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        logService.debug("SERVLET", "Request -------------------------------");
        logService.debug("SERVLET", "year = " + year);
        logService.debug("SERVLET", "author = " + author);
        logService.debug("SERVLET", "price = " + price);
        logService.debug("SERVLET", "---------------------------------------");

        String view = new GalleryView().getView(year, author, minScore, price, orderby);


        logService.debug("SERVLET", "Response ------------------------------");
        logService.debug("SERVLET", view);
        logService.debug("SERVLET", "---------------------------------------");
        return Response.ok().type(MediaType.APPLICATION_JSON).entity(view).build();


    }



    @GET
    @Path("/info")
    public Response getInfoView(
            @QueryParam("objectId") String objectId,
            @HeaderParam("authorization") String authentication

    ) {
        try {
            authenticationService.authenticate(authentication);
        } catch (UserDoesNotExistException e) {
            return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\":\"USER_NOT_FOUND\"}").build();
        } catch (UnsupportedAuthenticationMethod unsupportedAuthenticationMethod) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        logService.debug("SERVLET", "Request -------------------------------");
        logService.debug("SERVLET", "Processing request for /info " + objectId);
        logService.debug("SERVLET", "---------------------------------------");


        return Response.ok().type(MediaType.APPLICATION_JSON).entity(new ObjectView().getView(objectId)).build();

    }

    @GET
    @Path("/add")
    public Response getAddToBasket(@QueryParam("objectId") String objectId,
                                   @HeaderParam("authorization") String authentication) {
        try {
            authenticationService.authenticate(authentication);
        } catch (UserDoesNotExistException e) {
            return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\":\"USER_NOT_FOUND\"}").build();
        } catch (UnsupportedAuthenticationMethod unsupportedAuthenticationMethod) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        logService.debug("SERVLET", "Request -------------------------------");
        logService.debug("SERVLET", "Processing request for /add " + objectId);
        logService.debug("SERVLET", "---------------------------------------");

        Basket basket = new Basket();
        DataStorage storage = new DataStorage(this.getClass().getClassLoader().getResource("data.json").getPath());


        Book[] books = storage.getData();
        boolean found = false;
        boolean available = false;
        if (objectId != null) {
            for (int i = 0; i < books.length; i++) {
                if (books[i].getGuid().equals(objectId)) {
                    if (books[i].isAvailable()) {
                        basket.addToBasket(books[i]);
                        found = true;
                    } else {
                        return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\": \"NOT_AVAILABLE\"}").build();
                    }
                }
            }
            if (!found) {
                //try other storage options
            }
        }
//"status":"ACKNOWLEDGED"
        return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\":\"ACKNOWLEDGED\"}").build();

    }


}