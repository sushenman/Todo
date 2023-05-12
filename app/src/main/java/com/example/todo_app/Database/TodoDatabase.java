package com.example.todo_app.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.todo_app.DateConverter;
import com.example.todo_app.Todo;

@Database(entities = Todo.class, version = 1)
@TypeConverters(DateConverter.class)
public abstract class TodoDatabase extends RoomDatabase {
    private static TodoDatabase instance;
    public abstract TodoDao todoDao();
    public static synchronized TodoDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                        ,TodoDatabase.class,"todo_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
