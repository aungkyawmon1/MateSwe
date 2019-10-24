package com.example.mateswe.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mateswe.entity.Book;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE id = :id")
    Book findByID(String id);

    @Query("SELECT COUNT(*) from book")
    int countBooks();

    @Query("SELECT * FROM book WHERE author LIKE :author")
    List<Book> searchAuthor(String author);

    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);
}
