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
    String password;
    Basket basket;


    public User(String id, String guid, String username, Double balance, Basket basket, String password) {
        this.id = id;
        this.guid = guid;
        this.username = username;
        this.balance = balance;
        this.basket = basket;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public String getUsername() {
        return username;
    }

    public Double getBalance() {
        return balance;
    }

    public Basket getBasket() {
        return basket;
    }

    public String getPassword() {
        return password;
    }
}
