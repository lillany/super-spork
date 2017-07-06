package data;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;


/**
 * Created by eadrdam on 06.07.17..
 */

public class Book {
    @SerializedName("_id")
    private String id;
    @SerializedName("guid")
    private String guid;
    @SerializedName("index")
    private int index;
    @SerializedName("isAvailable")
    private boolean isAvailable;
    @SerializedName("price")
    private double price;
    @SerializedName("pictures")
    private String picture;
    @SerializedName("score")
    private int score ;
    @SerializedName("author")
    private String author;
    @SerializedName("gender")
    private String gender;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("description")
    private String description;
    @SerializedName("firstPublished")
    private int firstPublished;
    @SerializedName("dateCreated")
    private Timestamp dateCreated;
    @SerializedName("genre")
    private String genre;
    @SerializedName("tags")
    private String[] tags;

    public Book(String id, String guid, int index, boolean isAvailable, double price, String picture, int score, String author, String gender, String publisher, String description, int firstPublished, Timestamp dateCreated, String genre, String[] tags) {

        this.id = id;
        this.guid = guid;
        this.index = index;
        this.isAvailable = isAvailable;
        this.price = price;
        this.picture = picture;
        this.score = score;
        this.author = author;
        this.gender = gender;
        this.publisher = publisher;
        this.description = description;
        this.firstPublished = firstPublished;
        this.dateCreated = dateCreated;
        this.genre = genre;
        this.tags = tags;
    }

    public Book() {

    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getGuid() {
        return guid;
    }
    
    public void setGuid(String guid) {
        this.guid = guid;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }

    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    public double getPrice() {
        return price;
    }

    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getPicture() {
        return picture;
    }

    
    public void setPicture(String picture) {
        this.picture = picture;
    }

    
    public int getScore() {
        return score;
    }

    
    public void setScore(int score) {
        this.score = score;
    }

    
    public String getAuthor() {
        return author;
    }

    
    public void setAuthor(String author) {
        this.author = author;
    }

    
    public String getGender() {
        return gender;
    }

    
    public void setGender(String gender) {
        this.gender = gender;
    }

    
    public String getPublisher() {
        return publisher;
    }

    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    
    public String getDescription() {
        return description;
    }


    
    public void setDescription(String description) {
        this.description = description;
    }

    
    public int getFirstPublished() {
        return firstPublished;
    }

    
    public void setFirstPublished(int firstPublished) {
        this.firstPublished = firstPublished;
    }

    
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    
    public String getGenre() {
        return genre;
    }

    
    public void setGenre(String genre) {
        this.genre = genre;
    }

    
    public String[] getTags() {
        return tags;
    }

    
    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
