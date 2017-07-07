package com.ericsson.etk.test.storage;

import com.ericsson.etk.test.log.LogService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.ericsson.etk.test.domain.Book;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by eadrdam on 06.07.17..
 */
public class DataStorage {

    private final LogService logService;
    private final String storagePath;

    public DataStorage(String storagePath) {
        this.storagePath = storagePath;
        logService = new LogService();
        logService.debug("DATA STORE", "Data Store Initialized on location : " + this.storagePath);
    }

    public Book[] getData() {
        return readData(storagePath);
    }

    public String getStoragePath() {
        return storagePath;
    }

    protected Book[] readData(String pathname) {
        Book[] books = null;
        try {


            Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-dd hh:mm:ss").create();
            JsonReader reader = new JsonReader(new FileReader(pathname));
            books = gson.fromJson(reader, Book[].class);



        } catch (IOException e) {
            logService.warn("DATA STORE", "Exception while readi file " + pathname, e);
            return  new Book[0];
        }
        return books;

    }
}
