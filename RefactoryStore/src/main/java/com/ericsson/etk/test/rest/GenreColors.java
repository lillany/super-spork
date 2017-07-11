package com.ericsson.etk.test.rest;

/**
 * Created by eadrdam on 11.07.17..
 */
public enum GenreColors {

    fantasy("fantasy","purple"),
    science_fiction("science fiction","teal"),
    thriller("thriller","grey"),
    drama("drama","magenta"),
    romance("romance","brown"),
    travel("travel","yellow"),
    mistery("","red"),
    adventure("","green"),
    educational("","olive"),
    unknown("","white");

    private String  color;
    private String genre;

    GenreColors(String genre, String color) {
        this.color = color;this.genre = genre;
    }

    public String getColor() {
        return color;
    }

    public String getGenre() {
        return genre;
    }

    public static  GenreColors getByGenreString(String genreString){
        if(genreString==null){
            return unknown;
        }
        if(genreString.equals(fantasy.getGenre()))
            return fantasy;
        if(genreString.equals(science_fiction.getGenre()))
            return science_fiction;
        if(genreString.equals(thriller.getGenre()))
            return thriller;
        if(genreString.equals(drama.getGenre()))
            return drama;
        if(genreString.equals(romance.getGenre()))
            return romance;
        if(genreString.equals(travel.getGenre()))
            return travel;
        if(genreString.equals(mistery.getGenre()))
            return mistery;
        if(genreString.equals(adventure.getGenre()))
            return adventure;
        if(genreString.equals(educational.getGenre()))
            return educational;

            return unknown;


    }

}
