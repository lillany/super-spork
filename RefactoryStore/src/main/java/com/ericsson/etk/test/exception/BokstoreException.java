package com.ericsson.etk.test.exception;

/**
 * Created by eadrdam on 11.07.17..
 */
public class BokstoreException extends Exception {

    String infoData;

    public BokstoreException(String infoData, String message) {
        super(message);
        this.infoData = infoData;
    }
}
