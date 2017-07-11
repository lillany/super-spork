package com.ericsson.etk.test.rest;

import com.ericsson.etk.test.auth.AuthenticationService;
import com.ericsson.etk.test.domain.Book;
import com.ericsson.etk.test.domain.User;
import com.ericsson.etk.test.exception.UnsupportedAuthenticationMethod;
import com.ericsson.etk.test.exception.UserDoesNotExistException;
import com.ericsson.etk.test.log.LogService;
import com.ericsson.etk.test.storage.BasketStorage;
import com.ericsson.etk.test.storage.DataStorage;
import com.ericsson.etk.test.storage.UserStorage;
import com.ericsson.etk.test.store.Basket;
import com.ericsson.etk.test.view.GalleryView;
import com.ericsson.etk.test.view.ObjectView;
import com.google.gson.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


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

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(view).getAsJsonArray();
        if (jsonArray.isJsonArray()) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement x = jsonArray.get(i);
                JsonObject asJsonObject = x.getAsJsonObject();
                GenreColors genre = GenreColors.getByGenreString(asJsonObject.get("genre").getAsString());
                asJsonObject.addProperty("color", genre.getColor());
                jsonArray.set(i, asJsonObject);
            }
        }

        view = new GsonBuilder().setPrettyPrinting().create().toJson(jsonArray);

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


        String view = new ObjectView().getView(objectId);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(view).getAsJsonObject();
        if (jsonObject.isJsonObject()) {
            JsonElement author1 = jsonObject.get("author");
            String author = author1.getAsString();
            GalleryView galleryView = new GalleryView();
            List<Book> otherBooks = galleryView.getBooks(null, author, null, null, null);
            JsonArray array = new JsonArray();
            for (Book b : otherBooks) {
                if (objectId != null && objectId.equals(b.getGuid()))
                    continue;
                array.add(new GsonBuilder().setPrettyPrinting().create().toJson(b));
            }
            jsonObject.add("otherBooksByAuthor", array);

        }


        view = new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject);


        return Response.ok().type(MediaType.APPLICATION_JSON).entity(view).build();

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

        BasketStorage basketStorage = new BasketStorage();
        //get user from session, not just Mark
        User user = new UserStorage().getUserByUsername("Mark");

        Basket basket = basketStorage.getBasketFromStorage(user.getGuid());
        if (basket == null) {
            basket = new Basket();
        }

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
        basketStorage.putBasketToStorage(user.getGuid(), basket);
        return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\":\"ACKNOWLEDGED\"}").build();

    }


    @GET
    @Path("/buy")
    public Response buyBasket(@HeaderParam("authorization") String authentication) {
        try {
            authenticationService.authenticate(authentication);
        } catch (UserDoesNotExistException e) {
            return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\":\"USER_NOT_FOUND\"}").build();
        } catch (UnsupportedAuthenticationMethod unsupportedAuthenticationMethod) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        logService.debug("SERVLET", "Request -------------------------------");
        logService.debug("SERVLET", "Processing request for /buy ");
        logService.debug("SERVLET", "---------------------------------------");

        BasketStorage basketStorage = new BasketStorage();
        //get user from session, not just Mark
        User user = new UserStorage().getUserByUsername("Mark");

        Basket basket = basketStorage.getBasketFromStorage(user.getGuid());
        if (basket == null) {
            basket = new Basket();
        }

        List<Book> books = basket.getBooks();

        ObjectView objectView = new ObjectView();
        Double price = 0D;
        for (Book local_book : books) {
            //check is available
            Book book = objectView.getBook(local_book.getGuid());
            if (!book.isAvailable()) {
                return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\": \"NOT_AVAILABLE\"}").build();
            }

            price += book.getPrice();

        }

        if(user.getBalance()<price) {
            return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\": \"INSUFFICIENT_FUNDS\"}").build();
        }



//"status":"ACKNOWLEDGED"
        basketStorage.removeBasketFromStorage(user.getGuid());
        return Response.ok().type(MediaType.APPLICATION_JSON).entity("{\"status\":\"ACKNOWLEDGED\"}").build();

    }

}