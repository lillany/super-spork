package com.ericsson.etk.test.store;


import com.ericsson.etk.test.domain.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eadrdam on 06.07.17..
 */
public class Basket {
    Map<String, Book> books;

    public Basket() {
        books = new HashMap<>();
    }

    public void addToBasket(Book book) {
        books.put(book.getGuid(), book);
    }

    public List<Book> getBooks() {
        List<Book> getBooksList = new ArrayList<>(books.size());
        while(books.entrySet().iterator().hasNext()){
            getBooksList.add((Book) books.entrySet().iterator().next());
        }
        return getBooksList;
    }

    public void removeFromBasket(String guid) {
        books.remove(guid);
    }

    public void removeFromBasket(Book book) {
        books.remove(book.getGuid());
    }


}
