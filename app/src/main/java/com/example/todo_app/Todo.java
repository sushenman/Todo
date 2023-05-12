package com.example.todo_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "todo_table")
@TypeConverters(DateConverter.class)
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private Date date;


    public Todo(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }



}
