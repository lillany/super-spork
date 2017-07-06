package com.ericsson.etk.test.view;

import com.ericsson.etk.test.log.LogService;
import com.ericsson.etk.test.storage.DataStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Book;

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

    public String getView(String year, String author, String minScore, String price) {

        Book[] books = dataStorage.getData();
        List<Book> returnBooks = new ArrayList<>();
        logService.debug("GALERY", "get View");

        if (year == null && author == null && minScore == null && price == null) {
            logService.debug("GALERY", "returning all");

            returnBooks = Arrays.asList(books);
        } else {

            for (int i = 0; i < books.length; i++) {
                int b_minScore = books[i].getScore();
                int b_year = books[i].getFirstPublished();
                String b_author = books[i].getAuthor();
                double b_price = books[i].getPrice();
                logService.debug("GALERY", "checking:\n\r" +
                        "year=" + year + " == " + b_year + "\r\n" +
                        "year=" + author + " == " + b_author + "\r\n" +
                        "year=" + minScore + " == " + b_minScore + "\r\n" +
                        "year=" + price + " == " + b_price + "\r\n"
                );

                if ((year == null || year.equals(b_year)) && (minScore == null || minScore.equals(b_minScore))
                        && (author == null || author.equals(b_author)) && (price == null || price.equals(b_price))) {
                    logService.debug("GALERY", "in condition");

                    returnBooks.add(books[i]);
                }
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        return gson.toJson(returnBooks);


    }
}
