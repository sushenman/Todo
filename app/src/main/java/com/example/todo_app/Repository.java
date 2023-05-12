package com.example.todo_app;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todo_app.Database.TodoDao;
import com.example.todo_app.Database.TodoDatabase;

import java.util.List;

public class Repository {
    private TodoDao todoDao;
    private LiveData<List<Todo>> allData;
  public  Repository(Application application)
  {
        TodoDatabase database = TodoDatabase.getInstance(application);
        todoDao = database.todoDao();
        allData = todoDao.getAllData();
  }
public void insertData(Todo todo)
{
   new InsertTask(todoDao).execute(todo);
}
    public void updateData(Todo todo)
    {
        new UpdateTask(todoDao).execute(todo);
    }
    public void deleteData(Todo todo)
    {
        new DeleteTask(todoDao).execute(todo);
    }
    public LiveData<List<Todo>> getAllData()
    {
        return allData;
    }

    public  void deleteAll()
    {
        new DeleteAllTask(todoDao).execute();
    }


    private static class DeleteAllTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao todoDao;
        private DeleteAllTask(TodoDao todoDao)
        {
            this.todoDao = todoDao;
        }
        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.deleteAll();
            return null;
        }
    }
    private static class InsertTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao todoDao;
        private InsertTask(TodoDao todoDao)
        {
            this.todoDao = todoDao;
        }
        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.insert(todos[0]);
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao todoDao;
        private DeleteTask(TodoDao todoDao)
        {
            this.todoDao = todoDao;
        }
        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.delete(todos[0]);
            return null;
        }
    }
    private static class UpdateTask extends AsyncTask<Todo,Void,Void>{
        private TodoDao todoDao;
        private UpdateTask(TodoDao todoDao)
        {
            this.todoDao = todoDao;
        }
        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.update(todos[0]);
            return null;
        }
    }
}
