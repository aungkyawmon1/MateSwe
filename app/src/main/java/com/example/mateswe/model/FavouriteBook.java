package com.example.mateswe.model;

public class FavouriteBook {
    private int image;
    private String bookName, author;

    public FavouriteBook(int image, String bookName, String author){
        this.image = image;
        this.bookName = bookName;
        this.author = author;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }
}
