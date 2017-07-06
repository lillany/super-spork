package com.ericsson.etk.test.rest;

import com.ericsson.etk.test.log.LogService;
import com.ericsson.etk.test.view.GalleryView;
import com.ericsson.etk.test.view.ObjectView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/store")
public class RefactoryStoreServlet {

    LogService logService = new LogService();


    @GET
    @Path("/{param}")
    public Response UnknownQueryResponse(@PathParam("param") String parameter) {

        logService.debug("SERVLET","Unknown request with path: " + parameter);
        return Response.ok().entity(parameter).build();
    }

    @GET
    @Path("/gallery")
    public Response getGalleryView(
                                   @MatrixParam("year") String year,
                                   @MatrixParam("author") String author,
                                   @MatrixParam("minScore") String minScore,
                                   @MatrixParam("price") String price

    ) {
        logService.debug("SERVLET","Processing request for galleryView");
        logService.debug("SERVLET","year = " + year);
        logService.debug("SERVLET","author = " + author);
        logService.debug("SERVLET","price = " + price);

        String view = new GalleryView().getView(year, author, minScore, price);
        logService.debug("SERVLET","Response ------------------------------");
        logService.debug("SERVLET",view);
        logService.debug("SERVLET","---------------------------------------");
        return Response.ok().type(MediaType.APPLICATION_JSON).entity(view).build();


   }

    @GET
    @Path("/info")
    public Response getInfoView(@QueryParam("objectId") String objectId) {
        logService.debug("SERVLET","Processing request for /info " + objectId);

        return Response.ok().entity(new ObjectView().getView(objectId)).build();

    }


}