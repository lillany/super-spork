package com.ericsson.etk.test.storage;

import com.ericsson.etk.test.domain.User;
import com.ericsson.etk.test.store.Basket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eadrdam on 06.07.17..
 */
public class UserStorage {
    private List<User> users = new ArrayList<>();

    public UserStorage() {
        users = new ArrayList<>(4);
        users.add(new User("1","1-a","John",65.78D,new Basket()));
        users.add(new User("2","2-a","Matthew",65.78D,new Basket()));
        users.add(new User("3","3-a","Mark",65.78D,new Basket()));
        users.add(new User("4","4-a","Luke",65.78D,new Basket()));
    }

    public UserStorage(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
