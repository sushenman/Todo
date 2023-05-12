package com.example.todo_app.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo_app.Repository;
import com.example.todo_app.Todo;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private static Repository repository;
    private static LiveData<List<Todo>> todoList;
    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        todoList = repository.getAllData();
    }
    public static void insert(Todo todo)
    {
        repository.insertData(todo);
    }
    public static void update(Todo todo)
    {
        repository.updateData(todo);
    }
    public static void delete(Todo todo)
    {
        repository.deleteData(todo);
    }
    public static LiveData<List<Todo>> getAllData()
    {
        return todoList;
    }
    public static void deleteAll()
    {
        repository.deleteAll();
    }

}
