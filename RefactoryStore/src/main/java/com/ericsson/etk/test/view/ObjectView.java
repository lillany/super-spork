package com.ericsson.etk.test.view;

import com.ericsson.etk.test.log.LogService;
import com.ericsson.etk.test.storage.DataStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ericsson.etk.test.domain.Book;

/**
 * Created by eadrdam on 05.07.17..
 */
public class ObjectView {

    private final DataStorage dataStorage = new DataStorage(this.getClass().getClassLoader().getResource("data.json").getPath());
    private LogService logService = new LogService();

    public String getView(String objectId) {


        Book b =
                getBook(objectId);
        if (b == null) {
            return "{}";
        }
        logService.debug("OBJECT VIEW", b.toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(b);
    }

    public Book getBook(String objectId) {
        Book[] books = dataStorage.getData();
        Book b = null;
        for (int i = 0; i < books.length; i++) {
            if (objectId != null && books[i].getGuid().equals(objectId)) {
                b = books[i];
                break;
            }
        }
        return b;
    }
}
