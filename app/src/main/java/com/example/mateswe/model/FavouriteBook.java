package com.example.mateswe.model;

public class FavouriteBook {
    private String bookName, author, image;

    public FavouriteBook(String image, String bookName, String author){
        this.image = image;
        this.bookName = bookName;
        this.author = author;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String  image) {
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
