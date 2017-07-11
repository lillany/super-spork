package com.ericsson.etk.test.storage;

import com.ericsson.etk.test.store.Basket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eadrdam on 11.07.17..
 */
public class BasketStorage {

    Map<String, Basket> inMemoryStorage = new HashMap<>();

    public void putBasketToStorage(String guid, Basket basket) {
        inMemoryStorage.put(guid, basket);
    }

    public Basket getBasketFromStorage(String guid) {
        return inMemoryStorage.get(guid);
    }

    public void removeBasketFromStorage(String guid) {
        inMemoryStorage.remove(guid);
    }


}
