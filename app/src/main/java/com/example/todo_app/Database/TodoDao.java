package com.example.todo_app.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo_app.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    public void insert (Todo todo);
    @Update
    public void update (Todo todo);
    @Delete
    public void delete (Todo todo);


    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("Select * from todo_table")
    public LiveData<List<Todo>> getAllData();

    //store for date
//
//    public LiveData<List<Todo>> getTodoByDate(String date);

}
