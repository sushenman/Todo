package com.example.todo_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.Database.TodoData;
import com.example.todo_app.Database.TodoViewModel;
import com.example.todo_app.databinding.ActivityMainBinding;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private TodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TodoViewModel viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TodoViewModel.class);


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TodoData.class);
                intent.putExtra("type", "Add");
                startActivityForResult(intent, 1);
            }

        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        RVAdapter RVAdapter = new RVAdapter(this);
        binding.recyclerView.setAdapter(RVAdapter);
        TodoViewModel.getAllData().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                RVAdapter.submitList(todos);
            }

        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    TodoViewModel.delete(RVAdapter.getTodo(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "note deleted", Toast.LENGTH_SHORT).show();

                } else if (direction == ItemTouchHelper.RIGHT) {

                    Intent intent = new Intent(MainActivity.this, TodoData.class);
                    intent.putExtra("type", "update");

                    intent.putExtra("title", RVAdapter.getTodo(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("description", RVAdapter.getTodo(viewHolder.getAdapterPosition()).getDescription());
                    intent.putExtra("id", RVAdapter.getTodo(viewHolder.getAdapterPosition()).getId());
                    intent.putExtra("date", RVAdapter.getTodo(viewHolder.getAdapterPosition()).getDate());
                    startActivityForResult(intent, 2);
                    Log.d("TAG", "onSwiped: " + RVAdapter.getTodo(viewHolder.getAdapterPosition()).getId());
                }

            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("Name");
                String description = data.getStringExtra("Description");
                Date date = new Date(); // Use the current date and time
                Todo todo = new Todo(name, description, date);
                TodoViewModel.insert(todo);
                Toast.makeText(this, "note added", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("Name");
                String description = data.getStringExtra("Description");
                int id = data.getIntExtra("id", 0);
                Date date = new Date(); // Use the current date and time
                Todo todo = new Todo(name, description, date);
                todo.setId(id);
                TodoViewModel.update(todo);
                Toast.makeText(this, "note updated", Toast.LENGTH_SHORT).show();
            }
        }


    }
//make splash screen

public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
}
//create options menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.logout) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();
            return true;
        } else if(itemId == R.id.delete_all){
            TodoViewModel.deleteAll();
            Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

}


