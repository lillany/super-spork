package com.ericsson.etk.test.domain;

import com.ericsson.etk.test.store.Basket;

/**
 * Created by eadrdam on 06.07.17..
 */
public class User {

    String id;
    String guid;
    String username;
    Double balance;
    Basket basket;

    public User(String id, String guid, String username, Double balance, Basket basket) {
        this.id = id;
        this.guid = guid;
        this.username = username;
        this.balance = balance;
        this.basket = basket;
    }
}
