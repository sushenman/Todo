package com.example.todo_app.Database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todo_app.databinding.ActivityTodoDataBinding;

public class TodoData extends AppCompatActivity {
    ActivityTodoDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTodoDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if(type.equals("update"))
        {
            setTitle("update");
           binding.titletodo.setText(getIntent().getStringExtra("title"));
              binding.desc.setText(getIntent().getStringExtra("description"));
              int id = getIntent().getIntExtra("id",0);
              binding.add.setText("Update");

              binding.add.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent();
                      intent.putExtra("Name", binding.titletodo.getText().toString());
                      intent.putExtra("Description", binding.desc.getText().toString());

                      intent.putExtra("id", id);
                      setResult(RESULT_OK, intent);


                      finish();
                  }
              });
        }
        else {


            setTitle("Add Todo");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                        intent.putExtra("Name", binding.titletodo.getText().toString());
                        intent.putExtra("Description", binding.desc.getText().toString());
                        //intent the date and time

                        setResult(RESULT_OK, intent);


                    finish();
                }

            });
        };
    }
}