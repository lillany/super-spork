package com.ericsson.etk.test.view;

import com.ericsson.etk.test.log.LogService;
import com.ericsson.etk.test.storage.DataStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ericsson.etk.test.domain.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eadrdam on 05.07.17..
 */
public class GalleryView {

    private final DataStorage dataStorage = new DataStorage(this.getClass().getClassLoader().getResource("data.json").getPath());
    private LogService logService = new LogService();

    public GalleryView() {

    }




    public String getView(String year, String author, String minScore, String price, String orderBy) {

        List<Book> returnBooks = getBooks(year, author, minScore, price, orderBy);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        return gson.toJson(returnBooks);


    }

    public List<Book> getBooks(String year, String author, String minScore, String price, String orderBy) {
        Book[] books = dataStorage.getData();
        List<Book> returnBooks = new ArrayList<>();
        logService.debug("GALLERY", "get View");

        int order = 0; //index
        if(orderBy==null){
            order =0;
        }else if (orderBy.equals("author")) {
            order = 1;
        } else if (orderBy.equals("Title")) {
            order = 2;
        }


        if (year == null && author == null && minScore == null && price == null) {
            logService.debug("GALLERY", "returning all");

            returnBooks = Arrays.asList(books);

        } else {

            for (int i = 0; i < books.length; i++) {
                int b_minScore = books[i].getScore();
                int b_year = books[i].getFirstPublished();
                String b_author = books[i].getAuthor();
                double b_price = books[i].getPrice();

                if ((year == null || year.equals(b_year)) && (minScore == null || minScore.equals(b_minScore))
                        && (author == null || author.equals(b_author)) && (price == null || price.equals(b_price))) {
                    logService.debug("GALLERY", "in condition");

                    returnBooks.add(books[i]);
                }
            }
        }
        return returnBooks;
    }
}
