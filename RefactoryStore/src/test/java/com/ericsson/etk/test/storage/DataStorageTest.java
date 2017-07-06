package com.ericsson.etk.test.storage;

import com.google.gson.Gson;
import data.Book;
import org.junit.Test;

/**
 * Created by eadrdam on 06.07.17..
 */

public class DataStorageTest {

    @Test
    public void testSerialization() {

        String path = this.getClass().getClassLoader().getResource("data.json").getPath();
        DataStorage storage = new DataStorage(path);
        Book[] books = storage.readData(path);
        Gson gson = new Gson();
        System.out.println(gson.toJson(books));

    }

}